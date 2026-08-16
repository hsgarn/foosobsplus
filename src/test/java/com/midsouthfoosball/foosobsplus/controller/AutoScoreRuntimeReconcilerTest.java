package com.midsouthfoosball.foosobsplus.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.Test;

import com.midsouthfoosball.foosobsplus.model.TableConnection;

class AutoScoreRuntimeReconcilerTest {
	@Test void ordinaryEditsDoNotTriggerRebuild() {
		assertFalse(AutoScoreRuntimeReconciler.plan(List.of(connection("id-a", "Old", "10.0.0.1")), List.of(connection("id-a", "Renamed", "10.0.0.9")), 0, Set.of(0)).structuralChange());
	}
	@Test void deletingMiddlePreservesCorrectResourcesAndConnection() {
		List<TableConnection> old = List.of(connection("a", "A", "10.0.0.1"), connection("b", "B", "10.0.0.2"), connection("c", "C", "10.0.0.3"));
		var plan = AutoScoreRuntimeReconciler.plan(old, List.of(old.get(0).copy(), old.get(2).copy()), 2, Set.of(1, 2));
		assertTrue(plan.structuralChange()); assertEquals(List.of(0, 2), plan.newToOldIndex()); assertEquals(List.of(1), plan.removedOldIndices());
		assertEquals(1, plan.activeNewIndex()); assertEquals(Set.of(1), plan.reconnectNewIndices());
	}
	@Test void deletingActiveTableFallsBackAndCannotReconnectRemovedManager() {
		List<TableConnection> old = List.of(connection("a", "A", "10.0.0.1"), connection("b", "B", "10.0.0.2"));
		var plan = AutoScoreRuntimeReconciler.plan(old, List.of(old.get(1).copy()), 0, Set.of(0));
		assertEquals(0, plan.activeNewIndex()); assertEquals(List.of(0), plan.removedOldIndices()); assertTrue(plan.reconnectNewIndices().isEmpty());
	}
	@Test void reorderProducesNewListenerIndexMapping() {
		List<TableConnection> old = List.of(connection("a", "A", "10.0.0.1"), connection("b", "B", "10.0.0.2"));
		var plan = AutoScoreRuntimeReconciler.plan(old, List.of(old.get(1).copy(), old.get(0).copy()), 0, Set.of(0));
		assertEquals(List.of(1, 0), plan.newToOldIndex()); assertEquals(1, plan.activeNewIndex()); assertEquals(Set.of(1), plan.reconnectNewIndices());
	}
	@Test void newTableHasNoOldResource() {
		TableConnection old = connection("a", "A", "10.0.0.1");
		assertEquals(List.of(0, -1), AutoScoreRuntimeReconciler.plan(List.of(old), List.of(old.copy(), connection("b", "B", "10.0.0.2")), 0, Set.of()).newToOldIndex());
	}
	private static TableConnection connection(String id, String label, String address) { return new TableConnection(id, label, address, "5051", false, false, "", ""); }
}
