package com.midsouthfoosball.foosobsplus.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.midsouthfoosball.foosobsplus.model.TableConnection;

/** Pure stable-ID mapping used when saved AutoScore rows change structure. */
public final class AutoScoreRuntimeReconciler {
	private AutoScoreRuntimeReconciler() {}

	public record Plan(boolean structuralChange, List<Integer> newToOldIndex,
			List<Integer> removedOldIndices, int activeNewIndex, Set<Integer> reconnectNewIndices) {}

	public static Plan plan(List<TableConnection> oldConnections, List<TableConnection> newConnections,
			int activeOldIndex, Set<Integer> connectedOldIndices) {
		boolean structural = oldConnections.size() != newConnections.size();
		for (int i = 0; !structural && i < oldConnections.size(); i++) {
			structural = !oldConnections.get(i).getId().equals(newConnections.get(i).getId());
		}
		Map<String, Integer> oldById = new HashMap<>();
		for (int i = 0; i < oldConnections.size(); i++) oldById.put(oldConnections.get(i).getId(), i);
		List<Integer> mapping = new ArrayList<>();
		Set<Integer> retainedOld = new java.util.HashSet<>();
		Set<Integer> reconnect = new java.util.HashSet<>();
		int activeNew = newConnections.isEmpty() ? -1 : 0;
		for (int i = 0; i < newConnections.size(); i++) {
			Integer old = oldById.get(newConnections.get(i).getId());
			mapping.add(old == null ? -1 : old);
			if (old != null) {
				retainedOld.add(old);
				if (old == activeOldIndex) activeNew = i;
				if (connectedOldIndices.contains(old)) reconnect.add(i);
			}
		}
		List<Integer> removed = new ArrayList<>();
		for (int i = 0; i < oldConnections.size(); i++) if (!retainedOld.contains(i)) removed.add(i);
		return new Plan(structural, List.copyOf(mapping), List.copyOf(removed), activeNew, Set.copyOf(reconnect));
	}
}
