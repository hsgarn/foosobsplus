# FoosOBSPlus
FoosOBSPlus is a flexible foosball score keeper and statistics program compatible with OBS Studio (Version 28 or higher) using web sockets and also plays well with Elagto's Stream Deck products.

## Overview
FoosOBSPlus sends your Foosball game data (Players, scores, game counts, time outs, etc.) to OBS Studio using OBS Studio's web sockets protocol.  Almost every field displayed in FoosOBSPlus can be sent OBS Studio and displayed to your live stream. FoosOBSPlus can also connect to an auto scoring system using a Raspberry Pico W and some lasers (see https://github.com/hsgarn/FoosScore for more information.)

FoosOBSPlus Main Screen:
<img align="left" width="850" height="630" src="https://github.com/hsgarn/foosOBSPlus/blob/master/foosOBSPlusScreen1.png">

OBS Studio scene utilizing FoosOBSPlus to display various data:
<img align="left" width="1920" height="1090" src="https://github.com/hsgarn/foosOBSPlus/blob/master/foosOBSPlusScreen2.png">

## Setup
FoosOBSPlus is a java program.  You can download the executable jar file from the Releases section. You will need to have at least Java 1.8 loaded and set to associate jar files with java. There are many resources on the web for how to load java and set windows up to run the jar file.  (see https://www.howtogeek.com/812583/how-to-open-jar-files-windows/ for example.)

## Settings
If running FoosOBSPlus for the first time, it will create a set of properties files with the default properties in the folder in which the program is running. These property files contain the settings for the operating parameters, sources, hot keys and more.

### Operating Parameters
FoosOBSPlus tries to make keeping track of a foosball match's progress as simple as possible.  To that end, there is a settings screen that contains parameters that affect how FoosOBSPlus will behave.  Click Edit then Settings then Parameters to get to the Parameter Settings screen.

<img width="640" height="480" src="https://github.com/hsgarn/foosOBSPlus/blob/master/foosOBSPlusSettings1.png">

Parameter Settings Page:
<img width="552" height="442" src="https://github.com/hsgarn/foosOBSPlus/blob/master/foosOBSPlusSettings2.png">

#### Points to Win
This is the number of points required to win a game.  This is only used if the Auto Increment Game checkbox is checked and the Rack Mode check box is unchecked. Once a team's score reaches this number, it will reset the scores to zero and increase the game counter for the team.  If the Announce Winner check box is set and the team has reached the number set in the Games to Win parameter, then the Team's name will be sent to the Match Winner OBS source along with the text specified in the Winner Prefix and Winner Suffix parameters.  Points to Win is also utilized to determine if it is meatball (both teams 1 point away from winning score in final game).

#### Max Win
The Max Win parameter is only used when the Win By parameter is greater than 1.  If a team has to win by more than 1 point, then Max Win is the maximum score a team can get and at that point it does not matter if they won by more than 1 point.

#### Win By
The Win By parameter is used to force a team to win by a specified number of points.  Default is 1 which means that a team only has to win by 1 point (normal game behavior).  Setting the parameter to 2 would require that a team win by two points.  If a team reaches the Points to Win value without being ahead by the Win By value, then the game continues until either a team wins by the Win By margin, or the Max Win value is reached.  Note: If Win By in Final Game Only is checked, then the Win By points are only considered in the last game of the match (prior games are win by 1).

#### Games to Win
The Games to Win parameter is used to determine when a match is won.  Specify the number of games required to win the match.  Default is 2 for a 2 out of 3 match.  Use 3 for a 3 out of 5 match. etc.  6 is the maximum value supported at this time.

#### Max Time Outs
The Max Time Outs parameter is used to set how many time outs each team has.  The default is 2.

#### Team 1 Color
This is the color of the player figures for Team 1. Default is Yellow. This is used to help recognize which side of the table Team 1 is on.

#### Team 2 Color
This is the color of the player figures for Team 2. Default is Black. This is used to help recognize which side of the table Team 2 is on.

#### Balls in Rack
This is the number of balls in the table when using a coin op table.  This is used in Rack Mode.  In Rack Mode, the winner is determined by whoever has the most points after the number of balls in the Balls in Rack field have been scored.  The default is 9.

#### Rack Mode
When checked, Rack Mode is on.  A game is completed in Rack Mode when the number of balls in the Balls in Rack field have been scored. Whoever scored the most, wins.  So if Balls in Rack is 9, then the game ends when 9 points have been scored (the score could be 8 to 1, or 5 to 4 or 3 to 6, etc).  This mode is typically played on pay tables with pick up games as people play the full rack before starting a new game.
When unchecked, Rack Mode is off and a game is completed after a team scores the number of points required to win a game.  This is the typical mode used for tournaments and is the default.

#### Shot Time
This is the time allowed to shoot the ball from the forward 3 rod or the goalie area in seconds.  The default is 15 seconds.

#### Pass Time
This is the time allowed to pass the ball from the 5 row to the 3 row in seconds.  The default is 10 seconds.

#### Time Out Time
This is the time allowed for a time out in seconds.  The default is 30 seconds.

#### Game Time
This is the time allowed between games in seconds.  The default is 90 seconds.

#### Recall Time (min)
This is the number of minutes a player may be on recall before forfeiting the match.  The default is 10 minutes.

#### Team 1 Last Scored
This parameter defines the text that will be sent to the Last Scored OBS source when Team 1 scores.  The default value is "<--- Last Scored".

#### Team 2 Last Scored
This parameter defines the text that will be sent to the Last Scored OBS source when Team 2 scores.  The default value is "Last Scored --->".

#### Clear Last Scored
This parameter defines the text that will be sent to the Last Scored OBS source when neither team has scored (after the Reset All button is clicked or a score is reduced by the - button).

#### Auto Increment Game checkbox
When checked, the game count for the team that just scored will be automatically incremented when their score is incremented and reaches the number of points required to win a game.

#### Announce Winner checkbox
When checked, the Match Winner OBS source will be populated with the winning team name (prefixed by the Winner Prefix parameter and suffixed by the Winner Suffix parameter) when the game count for a team reaches the Games to Win parameter value.  Use the Start Match, Reset Scores or Reset All buttons to clear the Match Winner for the next match.  Team names will be used if they are populated, otherwise the Forward and Goalie names display.  If team name, forward and goalie fields are all empty, then the match winner will not be displayed.

#### Winner Prefix
Text to be displayed in front of the winning teams name when the match is won.  The default is Match Winner:.

#### Winner Suffix
Text to be displayed after the winning teams name when the match is won.  The default is !!!.

#### Announce Meatball checkbox and text field
When checked, the Meatball OBS source will be populated with the text in the Announce Meatball parameter when both teams' scores are one point away from winning.  Once the score changes, the text in the Meatball OBS source will be automatically cleared.

#### Show Time Outs Used
When checked, the program will put the number of time outs used for each team in the Time Out OBS sources.  When unchecked, the program will put the number of time outs remaining for each team in the Time Out OBS sources.

#### Auto Capitalize Names
When checked, the program will automatically capitalize the first letter of each team's player's names.  If not checked, the team names are left as entered.

#### Win By in Final Game Only
When checked, a team must win by the margin in Win By parameter in the final game of the match only.  The prior games will be win by 1 point.  When unchecked, a team must always win by the margin in the Win By field in every game.

#### Show Skunk
When checked, the program will activate the OBS filter defined in the OBS Connect dialogue box when a team wins a game without the opposing team scoring a single point in the game.

#### CutThroat Mode
When checked, the program activates CutThroat Mode to support the game of CutThroat.  In CutThroat, three people play against each other. One player starts on the scoring side (Team 1) while the other players play on Team 2. The player on the scoring side always serves the ball and when scores, gets a point.  If the players on other side score, then everybody rotates so that Team 2 Forward moves to the scoring side. Team 2 Goalie moves to the Team 1 Forward position.  And the player who was on the scoring side moves to the Team 2 Goalie position.  The scores in this mode are appended to the players names.

### Sources
FoosOBSPlus sends most of its data to sources in OBS Studio so it can be displayed in a live stream.  The names of these sources are configurable if the default names do not suit you. The vast majority of these sources should be Text (GDI+) sources with the Read from file checkbox unchecked.  Exceptions to this will be specifically noted below.  To get to the sources configuration, click on Edit, then Settings, then Sources:

<img align="left" width="552" height="442" src="https://github.com/hsgarn/foosOBSPlus/blob/master/foosOBSPlusSettings3.png">

<img align="left" width="502" height="302" src="https://github.com/hsgarn/foosOBSPlus/blob/master/foosOBSPlusSettings5.png">

Below are the sources that can be configured:

#### Team 1
This is the source for Team 1's name(s). Default source is team1name.

#### Team 2
This is the source for Team 2's name(s). Default source is team2name.

#### Game Count 1
This is the source for Team 1's game count. Default source is gamecount1.

#### Game Count 2
This is the source for Team 2's game count. Default source is gamecount2.

#### Score 1
This is the source for Team 1's score. Default source is score1.

#### Score 2
This is the source for Team 2's score. Default source is score2.

#### Time Out 1
This is the source for Team 1's time outs used or remaining depending the Show Time Outs Used checkbox. Default source is timeout1.

#### Time Out 2
This is the source for Team 2's time outs used or remaining depending the Show Time Outs Used checkbox. Default source is timeout2.

#### Reset 1
This is the source for Team 1's Reset flag.  Default source is reset1.

#### Reset 2
This is the source for Team 2's Reset flag.  Default source is reset2.

#### Warn 1
This is the source for Team 1's Reset Warning flag.  Default source is warn1.

#### Warn 2
This is the source for Team 2's Reset Warning flag.  Default source is warn2.

#### Game Time
This is the source for the game clock time.  Default source is gametime.

#### Match Time
This is the source for the match time.  Default source is matchtime.

#### Table Name
This is the source for the table name.  Default source is tablename.

#### Last Scored
This is the source that holds the indicator for which team scored last.  Default source is lastscored.

#### Team 1 Forward
This is the source that holds Team 1's forward's name.  Default source is team1forward.

#### Team 1 Goalie
This is the source that holds Team 1's goalie's name.  Default source is team1goalie.

#### Team 2 Forward
This is the source that holds Team 2's forward's name.  Default source is team2forward.

#### Team 2 Goalie
This is the source that holds Team 2's goalie's name.  Default source is team2goalie.

#### Tournament
This is the source of a freeform text field that can be used for the name of the tournament or venue.  Default source is tournament.

#### Event
This is the source of a freeform text field that can be used for the name of the event being played (i.e. DYP, Open Singles, etc).  Default source is event.

#### Time Remaining
This is the source for the Time Remaining on the current timer.  Default source is timeremaining.

#### Timer
This is the source that holds the name of the current timer that is running (Shot, Pass, Game, Timeout, Recall). Default source is timerinuse.

#### Match Winner
This is the source that holds the Winner Prefix, Team's Name and Winner Suffix of the team that won the match.  Default source is matchwinner.

#### Meatball
This is the source that holds the Meatball text when a game is tied just prior to the final point.  Default source is meatball.

#### Aces 1
This is the source that holds the number of aces team 1 had done. Default source is aces1.

#### Aces 2
This is the source that holds the number of aces team 2 had done. Default source is aces2.

#### Stuffs 1
This is the source that holds the number of stuffs team 1 has done.  Default source is stuffs1.

#### Stuffs 2
This is the source that holds the number of stuffs team 2 has done.  Default source is stuffs2.

#### Breaks 1
This is the source that holds the number of breaks team 1 has gotten.  Default source is breaks1.

#### Breaks 2
This is the source that holds the number of breaks team 2 has gotten.  Default source is breaks2.

#### Team 1 Pass Attempts
This is the source that holds the number of pass attempts for team 1.  Default source is team1passattempts.

#### Team 1 Pass Completes
This is the source that holds the number of pass completions for team 1.  Default source is team1passcompletes.

#### Team 2 Pass Attempts
This is the source that holds the number of pass attempts for team 2.  Default source is team2passattempts.

#### Team 2 Pass Completes
This is the source that holds the number of pass completions for team 2.  Default source is team2passcompletes.

#### Team 1 Shot Attempts
This is the source that holds the number of shot attempts for team 1.  Default source is team1shotattempts.

#### Team 1 Shot Completes
This is the source that holds the number of shots made for team 1.  Default source is team1shotcompletes.

#### Team 2 Shot Attempts
This is the source that holds the number of shot attempts for team 2.  Default source is team2shotattempts.

#### Team 2 Shot Completes
This is the source that holds the number of shots made for team 2.  Default source is team2shotcompletes.

#### Team 1 Clear Attempts
This is the source that holds the number of clearing attempts for team 1.  Default source is team1clearattempts.

#### Team 1 Clear Completes
This is the source that holds the number of successful clears for team 1 from the goalie area to the 5 bar or beyond.  Default source is team1clearcompletes.

#### Team 2 Clear Attempts
This is the source that holds the number of successful clears for team 2 from the goalie area to the 5 bar or beyond.  Default source is team2clearcompletes.

#### Team 2 Clear Completes
This is the source that holds the number of successful clears for team 2 from the goalie area to the 5 bar or beyond.  Default source is team2clearcompletes.

#### Team 1 2-Bar Pass Attempts
This is the source that holds the number of pass attempts for team 1 from the 2-bar to the 5-bar or 3-bar.  Default source is team1twobarpassattempts.

#### Team 1 2-Bar Pass Completes
This is the source that holds the number of succesful pass completions for team 1 from the 2-bar to the 5 or 3-bar.  Default source is team1twobarpasscompletes.

#### Team 2 2-Bar Pass Attempts
This is the source that holds the number of pass attempts for team 2 from the 2-bar to the 5-bar or 3-bar.  Default source is team2twobarpassattempts.

#### Team 2 2-Bar Pass Completes
This is the source that holds the number of succesful pass completions for team 2 from the 2-bar to the 5 or 3-bar.  Default source is team2twobarpasscompletes.

#### Team 1 Pass Percent
This is the source that holds the successful passing percentage for team 1.  Default source is team1passpercent.

#### Team 2 Pass Percent
This is the source that holds the successful passing percentage for team 2.  Default source is team2passpercent.

#### Team 1 Shot Percent
This is the source that holds the successful shot percentage for team 1.  Default source is team1shotpercent.

#### Team 2 Shot Percent
This is the source that holds the successful shot percentage for team 2.  Default source is team2shotpercent.

#### Team 1 Clear Percent
This is the source that holds the successful clear percentatge for team 1.  Default source is team1clearpercent.

#### Team 2 Clear Percent
This is the source that holds the successful clear percentatge for team 2.  Default source is team2clearpercent.

#### Team 1 Scoring
This is the source that holds the number of scores for team 1.  Default source is team1scoring.

#### Team 2 Scoring
This is the source that holds the number of scores for team 2.  Default source is team2scoring.

#### Team 1 3-Bar Scoring
This is the source that holds the number of scores from the 3-bar for team 1.  Default source is team1threebarscoring.

#### Team 2 3-Bar Scoring
This is the source that holds the number of scores from the 3-bar for team 2.  Default source is team2threebarscoring.

#### Team 1 5-Bar Scoring
This is the source that holds the number of scores from the 5-bar for team 1.  Default source is team1fivebarscoring.

#### Team 2 5-Bar Scoring
This is the source that holds the number of scores from the 5-bar for team 2.  Default source is team2fivebarscoring.

#### Team 1 2-Bar Scoring
This is the source that holds the number of scores from the 2-bar for team 1.  Default source is team1twobarscoring.

#### Team 2 2-Bar Scoring
This is the source that holds the number of scores from the 2-bar for team 2.  Default source is team2twobarscoring.

#### Team 1 Shots On Goal
This is the source that holds the number of shots on goal for team 1.  Default source is team1shotsongoal.

#### Team 2 Shots On Goal
This is the source that holds the number of shots on goal for team 2.  Default source is team2shotsongoal.

#### Show Scores
This is the source that is controlled by the Show Score/Hide Score toggle button in the OBS Panel on the main screen.  This is intended to be a Scene within OBS that contains all the scoring fields and their associated labels.  This scene can then be included in your main streaming scene.  When you want to keep score, click the Show Score button on the main screen.  When no one is keeping the score, click the Hide Score button which will turn the scene in the Show Scores box off.
The following sources would typically be contained in this scene:
Last Scored, Time Out 1, Time Out 2, Game Count 1, Game Count 2, Score 1, Score 2
Also include any labels that would look out of place without the above fields.

#### Show Timer
This is the source that shows the time remaining.  This should be a Window Capture source in OBS. The window should be setup as follows:
<img width="500" height="200" src="https://github.com/hsgarn/foosOBSPlus/blob/master/foosOBSPlusSettingsTimerWindow.png">

#### Save
Click the save button to save any source changes made.

#### Cancel
Click the cancel button to discard any source changes made.

#### Restore Defaults
Click the Restore Defaults button to restore the default sources.

### Hot Keys
FoosOBSPlus uses buttons to do various functions such as increase or decrease scores, switch sides, reset game counts, start timers, etc.  Each button can have a Hot Key assigned to it.  Pressing ALT plus the assigned Hot Key for the button will function just like pressing the actual button.  Unfortunately, there are more buttons than available hot keys so you can not assign a hot key to every button. The hot keys can be used in Stream Deck commands to make operating FoosOBSPlus a simple push button affair.  TouchPortal is another program that can be used to activate the buttons in FoosOBSPlus.  These will require that AutoHotKey be installed. (See https://www.autohotkey.com/docs/Tutorial.htm).  FoosOBSPlus will generate the AutoHotKey scripts for any hot keys defined (see the Generate AutoHotKey Scripts button below).

<img align="left" width="552" height="442" src="https://github.com/hsgarn/foosOBSPlus/blob/master/foosOBSPlusSettings4.png">

<img align="left" width="802" height="442" src="https://github.com/hsgarn/foosOBSPlus/blob/master/foosOBSPlusSettings6.png">

#### Start Match
Assigns the hot key for the Start Match button.  Default hot key is b.

#### Pause Match
Assigns the hot key for the Pause Match button.  Default hot key is ,.

#### Start Game
Assigns the hot key for the Start Game button.  Default hot key is ..

#### Reset(Team 1)
Assigns the hot key for the Reset button for Team 1.  Default hot key is unassigned.

#### Reset(Team 2)
Assigns the hot key for the Reset button for Team 2.  Default hot key is unassigned.

#### Warn(Team 1)
Assigns the hot key for the Warn button for Team 1. Default hot key is unassigned.

#### Warn(Team 2)
Assigns the hot key for the Warn button for Team 2. Default hot key is unassigned.

#### Switch Reset/Warns
Assigns the hot key to swap the Reset and Warn flags for Team 1 with Team 2.  Default hot key is unassigned.

#### Team Names
##### Clear(Team 1)
Assigns the hot key for the Clear button for Team 1's name. Default hot key is f.

##### Team 1 Switch Positions
Assigns the hot key to Switch Team 1 and Team 2's names. Default hot key is t.

##### Clear(Team 2)
Assigns the hot key for the Clear button for Team 2's name. Default hot key is h
##### Team 2 Switch Positions
Assigns the hot key for the Switch button for Team 2's names. Default hot key is m.

##### Switch Teams
Assigns the hot key to swap Team 1's and Team 2's names (Team, Forward & Goalie) with each other.  Default hot key is e.

#### Scores
##### -(Team 1)
Assigns the hot key for the - button for Team 1's score. Default hot key is 4.

##### +(Team 1)
Assigns the hot key for the + button to increase Team 1's score. Default hot key is 1.

##### -(Team 2)
Assigns the hot key for the - button for Team 2's score. Default hot key is 8.

##### +(Team 2)
Assigns the hot key for the + button to increase Team 2's score. Default hot key is 2.

##### Switch Scores
Assigns the hot key to Switch Team 1 and Team 2's scores.  Default hot key is unassigned.

#### Switch Sides
Assigns the hot key to swap all Team 1's data with Team 2's data.  Use this when the teams switch sides. Default hot key is w.

#### Clear All
Assigns the hot key for the Clear All button in the Switch Panel.  Default hot key is unassigned.

#### Game Counts
##### -(Team 1)
Assigns the hot key for the - button for Team 1's score. Default hot key is j.

##### +(Team 1)
Assigns the hot key for the + button to increase Team 1's game count. Default hot key is 5.

##### -(Team 2)
Assigns the hot key for the - button for Team 2's score. Default hot key is i.

##### +(Team 2)
Assigns the hot key for the + button to increase Team 2's game count. Default hot key is 6.

##### Switch Game Counts
Assigns the hot key to switch the game counts. Team 1's game count will be swapped with Team 2's game count.  Default hot key is unassigned.

#### Time Outs
##### Return TO(Team 1)
Assigns the hot key to return a time out to Team 1's available pool of timeouts or used pool of time outs depending on how the Show Time Outs Used setting is set.  Default hot key is n.

##### Use TO(Team 1)
Assigns the hot key to use a time out for Team 1.  Default hot key is 9.

##### Return TO(Team 2)
Assigns the hot key to return a time out to Team 2's available pool of timeouts or used pool of time outs depending on how the Show Time Outs Used setting is set.  Default hot key is 1.

##### Use TO(Team 2)
Assigns the hot key to use a time out for Team 2.  Default hot key is 0.

##### Switch Time Outs
Assigns the hot key to switch the time out counts. Team 1's time out count will be swapped with Team 2's time out count.  Default hot key is [.

#### Reset Names
Assigns the hot key for the Reset Names button in the Reset Panel.  Default hot key is unassigned.

#### Reset Scores
Assigns the hot key for the Reset Scores button in the Reset Panel.  Default hot key is 3.

#### Reset Game Counts
Assigns the hot key to reset Team 1's and Team 2's game counts to 0.  Default hot key is 7.

#### Reset Time Outs
Assigns the hot key to reset Team 1's and Team 2's time outs to 0 or the max time outs depending on how the Show Time Outs Used setting is set.  Default hot key is -.

#### Reset Reset/Warn
Assigns the hot key to clear the Reset and Warn flags for both Team 1 and Team 2.  Default hot key is unassigned.

#### Reset All
Assigns the hot key to clear the game counts, scores, time outs, reset flags and warn flags for both Team 1 and Team 2.  Default hot key is a.

#### Timers
##### Start(Shot)
Assigns the hot key to start the Shot timer.  Default hot key is s.

##### Start(Pass)
Assigns the hot key to start the Pass timer.  Default hot key is p.

##### Start(Time Out)
Assigns the hot key to start the Time Out timer.  Default hot key is o.

##### Start(Game)
Assigns the hot key to start the Game timer.  Default hot key is g.

##### Start(Recall)
Assigns the hot key to start the Recall timer.  Default hot key is c.

##### Reset Timer
Assigns the hot key to reset the timer to 0 regardless of what it is currently timing.  Default hot key is r.

#### Undo
Assigns the hot key for the Undo button.  Default hot key is u.

#### Redo
Assigns the hot key for the Redo button.  Default hot key is d.

#### Switch Forwards
Assigns the hot key for switching only the names of the Forwards of Team 1 and Team 2.  Default hot key is ;.

#### Switch Goalies
Assigns the hot key for switching only the names of the Goalies of Team 1 and Team 2.  Default hot key is x.

#### Show Skunk
Assigns the hot key for the Show Skunk button. Default hot key is k.

#### Start Stream
Assigns the hot key for the Start Stream button. Default hot key is z.

#### AutoHotKey Script Path
This is the path to where the AutoHotKey scripts will be generated.  The default is C:\FoosOBSPlusScripts\.

#### Generate AutoHotKey Scripts
This button will generate an AutoHotKey script for each defined hot key.  The basic idea of the scripts is to bring the FoosOBSPlus window into focus and then press the hot key for the associated hot key for the action desired. It then moves the FoosOBSPlus window to the bottom of the screen so that your OBS program can stay the main focus.  The scripts will be placed into the directory specified in the AutoHotKey Script Path field.

#### Save
Click the save button to save any hot key changes made.

#### Cancel
Click the cancel button to discard any hot keye changes made.

#### Restore Defaults
Click the Restore Defaults button to restore the default hot keys.

### Filters
FoosOBSPlus can trigger OBS Filters when certain events occur.  For instance, when a time out occurs for team 1, a filter can be triggered to show a ref calling time out.  The filter is responsible for turning itself off.    To get to the filters configuration, click on Edit, then Settings, then Filters:

<img align="left" width="300" height="220" src="https://github.com/hsgarn/foosOBSPlus/blob/master/foosOBSPlusSettings9.png">

<img align="left" width="502" height="302" src="https://github.com/hsgarn/foosOBSPlus/blob/master/foosOBSPlusSettings10.png">

Below are the filters that can be configured:

#### Team 1 Score
This is the name of the filter activated when Team 1 scores.

#### Team 2 Score
This is the name of the filter activated when Team 2 scores.

#### Team 1 Win Game
This is the name of the filter activated when Team 1 wins a game.

#### Team 2 Win Game
This is the name of the filter activated when Team 2 wins a game.

#### Team 1 Win Match
This is the name of the filter activated when Team 1 wins a match.

#### Team 2 Win Match
This is the name of the filter activated when Team 2 wins a match.

#### Team 1 Time Out
This is the name of the filter activated when Team 1 calls a time out.

#### Team 2 Time Out
This is the name of the filter activated when Team 2 calls a time out.

#### Team 1 Reset
This is the name of the filter activated when the Team 1 Reset button is pressed.

#### Team 2 Reset
This is the name of the filter activated when the Team 2 Reset button is pressed.

#### Team 1 Warn
This is the name of the filter activated when the Team 1 Warn button is pressed.

#### Team 2 Warn
This is the name of the filter activated when the Team 2 Warn button is pressed.

#### Team 1 Switch Positions
This is the name of the filter activated when the Team 1 Switch Positions button is pressed.

#### Team 2 Switch Positions
This is the name of the filter activated when the Team 2 Switch Positions button is pressed.

#### Team 1 Skunks
This is the name of the filter activated when Team 1 skunks Team 2.

#### Team 2 Skunks
This is the name of the filter activated when Team 2 skunks Team 1.

#### Start Match
This is the name of the filter activated when the Start Match button is pressed.

#### Start Game
This is the name of the filter activated when the Start Game button is pressed.

#### Switch Sides
This is the name of the filter activated when the Switch Sides button is pressed.

#### Save
Click the save button to save any filter changes made.

#### Cancel
Click the cancel button to discard any filter changes made.

#### Restore Defaults
Click the Restore Defaults button to restore the default filters.

### Partner Program
FoosOBSPlus can read player names from files.  For example, we have an Access program called Partner Program and when a match is called in this program, it writes the players' names to 4 files. The directory and filenames can be set in the Partner Program Settings window.

<img align="left" width="552" height="442" src="https://github.com/hsgarn/foosOBSPlus/blob/master/foosOBSPlusSettings7.png">

<img align="left" width="600" height="400" src="https://github.com/hsgarn/foosOBSPlus/blob/master/foosOBSPlusSettings8.png">

#### Select Path
The Select Path allows you to choose the directory that will contain the files of the players' names.  Optionally you can just type the path in the box to the right of the Select Path button.

#### Player 1
This is the name of the file containing the name of Team 1's forward.  Default is Player1.txt.

#### Player 2
This is the name of the file containing the name of Team 1's goalie.  Default is Player2.txt.

#### Player 3
This is the name of the file containing the name of Team 2's forward.  Default is Player3.txt.

#### Player 4
This is the name of the file containing the name of Team 2's goalie.  Default is Player4.txt.

#### Save
Click the save button to save any changes made.

#### Cancel
Click the cancel button to discard any changes made.

#### Restore Defaults
Click the Restore Defaults button to restore the default file names.

### Statistics
This has not been implemented yet. But will contain the sources for the Statistics generated for the current game.  Probably will only complete this section if someone expresses an interest.

#### Save
Click the save button to save any changes made.

#### Cancel
Click the cancel button to discard any changes made.

#### Restore Defaults
Click the Restore Defaults button to restore the default file names.

## FoosOBSPlus Main Screen
The FoosOBSPlus Main Screen is divided into 11 panels.  Each panel has its own controls and purpose.  Below is a description of each panel.

### Table Information
#### Tournament Name
#### Event Name
#### Table Name
#### Clear
#### Load
#### Set

### Match Information
#### Start Event
#### Start Match
#### Pause Match
#### End Match
#### Start Game
#### Start Time
#### Elapsed Time
#### Game Time
#### Game Table

### Team 1 Information
#### Team Name
#### Forward Name
#### Goalie Name
#### Switch Positions
#### Score
#### Score -
#### Score +
#### Game Count
#### Game Count -
#### Game Count +
#### Time Out Count
#### Return TO
#### Use TO
#### Reset
#### Warn
#### Time Since Last Scored
#### Clear

### Team 2 Information
This panel is identical to Team 1 Information panel but is for tracking Team 2's information.

### Timer Panel
#### Active Timer
#### Shot Timer Start
#### Pass Timer Start
#### Time Out Timer Start
#### Game Timer Start
#### Recall Timer Start
#### Reset Timer

### OBS Panel
#### Connect
#### Update OBS
#### Show Score/Hide Score
#### Show Timer/Hide Timer
#### Disconnect
#### Fetch OBS
#### Enable Skunk/Disable Skunk
#### Start Stream

### AutoScore Panel
#### Connect
#### Settings
#### Disconnect
#### Ignore Sensors

### Switch Panel
#### Switch Sides
#### Switch Teams
#### Switch Forward
#### Switch Goalie
#### Switch Scores
#### Switch Game Counts
#### Switch Time Outs
#### Switch Reset/Warns
#### Clear All
#### Last Scored

### Reset Panel
#### Reset Names
#### Reset Scores
#### Reset Game Counts
#### Reset Time Outs
#### Reset Reset/Warns
#### Reset All

### Statistics Entry Panel
#### Foosball Code
#### History
#### Clear
#### Undo
#### Redo

### Statistics Display Panel
#### Passing
#### Shooting
#### Clearing
#### 2-Bar Passing
#### Shots On Goal
#### Scoring
#### 3-Bar
#### 5-Bar
#### 2-Bar
#### Breaks
#### Stuffs
#### Aces

## Revision History
v1.111 12/04/2022</br>
Add Balls In Rack and Rack Mode to Parameters.</br>
Add Rack Mode scoring logic.</br>
Update README.md.</br>
Setup the base Statistics Parameters window.</br>
</br>
v1.109 11/27/2022</br>
Change Show Skunk in OBS Panel to Enable Skunk.</br>
Sync OBS Panel Enable Skunk with Parameters Enable Skunk.</br>
Remove Skunk Filter from OBS Connect panel and Settings.</br>
Show Score & Show Timer now toggle button text.</br>
Query OBS for state of Timer Source when connected and </br>
update Show Timer button on OBS Panel.</br>
Closing Timer Window will deactive Timer Source in OBS.</br>
</br>
v1.108 11/25/2022</br>
Add ability to set filters for a variety of events (score, game/match win, time out, etc)</br>
Add game count for cutthroat mode</br>
</br>
v1.107 11/25/2022</br>
Always rebuild OBS Controller prior to connecting</br>
Add auto capitalize names logic</br>
</br>
v1.106 </br>
Remove more filename support.</br>
Add error checking for obs connection parameters and auto login on start.</br>
</br>
v1.105 11/18/2022</br>
Remove support for using filenames with OBS</br>
</br>
v1.104 11/18/2022</br>
Remove Billiards</br>
</br>
v1.103 11/15/2022</br>
Convert to obs-websockets-java v2.0.0</br>
Cleanup some java warnings</br>
</br>
v1.102 11/04/2022</br>
Check for duplicate hot keys and warn</br>
Add Generate AutoHotKey Scripts button and logic</br>
</br>
v1.101 11/02/2022</br>
Add hotkey for Start Stream button.</br>
Change default hotkeys.</br>
</br>
v1.100 10/24/2022</br>
Add Start Event Button and tweak some panel layouts.</br>
</br>
v1.099 10/23/2022</br>
Add Partner Program Settings screen.</br>
</br>
v1.098 10/11/2022</br>
Split Start Match button into Start Match and End Match buttons.</br>
Add Start Stream button to start a stream timer.</br>
Make Start Match/Game buttons write stream time and players to file.</br>
Write players and scores to file when game/match is won.</br>
</br>
v1.097 09/24/2022</br>
</br>
Fix bug in file whatcher logic when less than 4 players</br>
</br>
v1.096 09/20/2022</br>
</br>
Autoscore now receives Pin number in addition to Team number for easier debugging.
</br>
v1.095 09/03/2022</br>
</br>
Hack in CutThroat Mode scoring</br>
</br>
v1.094 08/30/2022</br>
</br>
Switch Positions, Forwards, Goalies, Teams will reset scores/timeouts/resetwarns if match has been won</br>
</br>
v1.093 08/28/2022</br>
</br>
Add ShowSkunk setting</br>
Add ShowSkunk button</br>
Parameterize Scene, Score, Timer sources and Skunk filter</br>
Add CutThroat Mode to rotate player names</br>
</br>
v1.092 08/27/2022</br>
</br>
Add Skunk detection and action.</br>
</br>
v1.091 08/21/2022</br>
</br>
Forward/Goalie/Team name change will reset scores/timeouts/resetwarns if match has been won</br>
</br>
v1.090 08/20/2022</br>
</br>
Change auto scoring debug messages slightly</br>
Fix Increase Game Count buttons not working</br>
Reset scores on game wins but not match win</br>
Pass/Shot Start Timer buttons will reset scores/timeouts/resetwarns if match has been won</br>
Only show AutoScore green connection icon if we actually get connected.</br>
</br>
v1.089 07/04/2022</br>
</br>
Add ignore sensors button.</br>
Fix timer bug.</br>
</br>
v1.088 07/02/2022</br>
</br>
Scan folder for player name changes</br>
Keep score at end of each game and match until next point is scored.</br>
Known issue: timer bug - timer does not move to next game when it should.</br>
</br>
v1.087 06/27/2022</br>
</br>
Add Auto Score Config window</br>
Add AutoConnect, DetailLog to AutoScore Settings</br>
</br>
v1.086 06/24/2022</br>
</br>
Get cancel autoscore working</br>
Refocus after score shows timer window</br>
Add release date to about</br>
Add autoscoremainpanel</br>
Automatically reset and start match on point after match has been won</br>
</br>
v1.085 06/19/2022</br>
Fix load autoscore properties on startup</br>
Save valid data in autoscore.properties</br>
</br>v1.084 06/19/2022</br>
Add AutoScore window</br>
Add auto score logic to update scores when score server sends team scored<br/>
<br/>
v1.083 06/05/2022<br/>
Add OBSPanel and buttons<br/>
<br/>
v1.082 05/22/2022<br/>
Fix LastScored display bug<br/>
<br/>
v1.081 05/22/2022<br/>
Fix connect error when obs not running<br/>
Fix nullpointerexception if OBS not running and autoconnect on<br/>
Fix README some more<br/>
Fix bug in currentModifier where it was not getting reset once set<br/>
Bump gson to version 2.8.9 for security vulnerability remediation<br/>
Change clear definition to from 2 bar to past same team's 5 bar.<br/>
<br/>
v1.080 05/17/2022<br/>
Add Aces to stats<br/>
Add table name to sources/filenames so it can be used in OBS.<br/>
Only save password if save password checked<br/>
Add option to Update on Connect (needs more work)<br/>
<br/>
v1.079 09/10/2021<br/>
DeDupe LastScoredClock, LastScoredWindowPanel, LastScoredWindowFrame classes<br/>
<br/>
v1.078 09/10/2021<br/>
Add display of WebSocket Version on OBS connect<br/>
Update Team data from OBS Screen data using WebSocket<br/>
<br/>
v1.077 08/17/2021<br/>
Make start/reset timer commands/buttons show timer window.<br/>
<br/>
v1.076 08/15/2021<br/>
Add OBSScene property<br/>
Add Set Scene button on OBS Connect Panel<br/>
Add custom tab traversal for Team panels<br/>
Split OBS parameters into own properties object/file<br/>
Always show Connect... menu item enabled<br/>
<br/>
v1.075 08/01/2021<br/>
Remove FileName from the filename properties so they would be same as source properties.<br/>
Write data to file or web socket in Team, Table, Match models.<br/>
Removed more unnecessary configProp settings in save logic.<br/>
Stopped passing settings in method when settings already in class.<br/>
Fix handling for missing config files<br/>
<br/>
v1.074 07/31/2021<br/>
Split config.properties into control.properties, filename.properties, hotkey.properties.<br/>
Add source.properties<br/>
Clean up writes in all the Model classes.<br/>
Remove all the unnecessary config variables and just use the properties maps.<br/>
<br/>
v1.073 07/28/2021<br/>
Added setTextGDIPlus to obs-websocket-java v1.3. Supports source, text, readfromfile parameters.<br/>
Add ability for writeScores to use websocket or file.<br/>
<br/>
v1.072 07/18/2021<br/>
Issue #18 Reset All, Reset Game Counts, Clear All buttons should reset currentGameNumber and repaint game tables.<br/>
<br/>
v1.071 07/13/2021<br/>
Fix SwitchPlayer1Hotkey and SwitchPlayer2Hotkey so defaults will load if not found in config.properties<br/>
Fix errors in some OBS calls.<br/>
ShowParse now also shows some OBS Commands in the connect window.<br/>
<br/>
v1.070 07/11/2021<br/>
Add Show Scores option to turn scores on/off in OBS<br/>
Clean up Main (remove code that was commented out)<br/>
Define obsSceneName and obsShowScoresSource<br/>
Add Switch Player1/Switch Player2 buttons<br/>
Add Close on Connect checkbox<br/>
<br/>
v1.069<br/>
Switch out mainFrame when gameType changes<br/>
Auto hide connection window on successful connection<br/>
Append text to connect window text box instead of overwriting and add time stamp<br/>
Extract some code to methods in Main<br/>
<br/>v1.068 02/06/2021<br/>
Show Connected/Disconnect icon in menu bar<br/>
<br/>
v1.067 02/02/2021<br/>
Add disconnect menu item<br/>
Only call obs commands when connected<br/>
Add CurrentScene to OBS model<br/>
<br/>
v1.066 01/31/2021<br/>
Add Sync OBS, 9 Ball, Show All, Hide All buttons for Ball Panel.<br/>
Add associated XPSO, XPNB, XPSA, XPHA commands for above buttons respectively.<br/>
<br/>
v1.065 01/29/2021<br/>
Add ball panel and logic to show/hide balls in obs<br/>
<br/>
v1.064 01/28/2021<br/>
Build OBS Connect screen GUI<br/>
Implement OBS Connect functionality<br/>
Enable/Disable connect/disconnect based on OBS Connection<br/>
<br/>
v1.063 01/22/2021<br/>
Add OBS Menu option with Connect sub item<br/>
Add OBSConnectFrame and OBSConnectPanel base classes<br/>
GameType prompt added in v1.062 release to switch external string context<br/>
<br/>
v1.062 12/31/2020<br/>
Externalize Strings in LastScored1WindowFrame, LastScored2WindowFrame, ParametersFrame, ParametersPanel, TimerWindowFrame<br/>
Consolidate Error and Global strings<br/>
<br/>
v1.061 12/31/2020<br/>
Externalize Strings in MainFrame, FileNamesFrame, FileNamesPanel, GameTableWindowFrame, HotKeysFrame, HotKeysPanel<br/>
<br/>
v1.060 12/30/2020<br/>
Add Game Type<br/>
Externalize Strings in Table, Timer, Stats Entry, Stats Display, Match, Team, Switch, Reset, About panels<br/>
Set Max Win points to Points to Win if Points to Win exceeds Max Win points.<br/>
<br/>
v1.059 12/26/2020<br/>
Add revision history to help page<br/>
Issue #17 Set help page to specifically start at #FoosOBSPlus<br/>
<br/>
v1.058 12/26/2020<br/>
Issue #17 Change help to call foosOBSPlus repository page instead of foosOBS repository page<br/>
Update announce match winner on help page.<br/>
<br/>
v1.057 12/26/2020<br/>
Issue #12 Changing Games To Win parameter should reload Match Panel and GameTableWindowPanel<br/>
Cap "Games to Win" at 6.<br/>
Fix bug if no names set and announce match winner triggered.<br/>
<br/>
v1.056 12/25/2020<br/>
Issue #13 If gamecounts loaded from file are too high, program errors.<br/>
Issue #16 Add Win by Final Game Only parameter and logic<br/>
Issue #15 For showing Match Winner, show Team Name if populated, otherwise, show player names.<br/>
<br/>
v1.055 12/24/2020<br/>
Issue #14 Add hotkeys for undo and redo buttons<br/>
<br/>
v1.054 9/27/2020<br/>
Change colors game table panel and window<br/>
Allow closing of view windows and keep view menu selection checkmarks in sync<br/>
Open independent windows side by side instead of on top of each other.<br/>
<br/>
v1.053 9/27/2020<br/>
Issue #11 Limit game table window to number of possible games based on parameters<br/>
Change highlight colors for match panel to see if new colors grow on me<br/>
<br/>
v1.052 9/13/2020<br/>
Toggle start match button label between Start Match and End Match.<br/>
<br/>
v1.051 9/13/2020<br/>
Added Import Stats and Export Stats options to the File Menu.<br/>
Implement isOffTable flag and add isDeadBall flag<br/>
<br/>
v1.050 9/12/2020<br/>
Refactored SettingsFrame and SettingsPanel to ParametersFrame and ParametersPanel for naming consistency.<br/>
<br/>
v1.050 9/9/2020 <br/>
Use try with resources in settings and OBSInterface<br/>
Refactor to use java.nio.file in place of java.io.file<br/>
<br/>
v1.049 9/7/2020<br/>
Add team name to Stats Display Window<br/>
Update stats display window when team name or player names change<br/>
<br/>
v1.048 9/7/2020<br/>
Issue #9 Start Match needs to clear match winner & meatball<br/>
<br/>
v1.047<br/>
Start game button should unpause match<br/>
Timer and Game Table windows no longer always on top<br/>
Increase/decrease game counts should update game table<br/>
Sync game table currentgamenumber with team game counts.<br/>
Main.autoProcessCodes created to parse codes in a string array.<br/>
MatchWon needs to Stop match and game timers<br/>
Game timer in game table window is briefly getting set to 0 when score increased.<br/>
Add ViewAllWindows checkbox menu item<br/>
<br/>
v1.046<br/>
A shot that goes in own team's goal should increase shot attempts for that team and not change stats for other team except scoring<br/>
Add names to statsdisplaypanel<br/>
<br/>
v1.045<br/>
Add fwd,goalie names to Game Table Window Panel<br/>
Update game tables when name or score related data changes<br/>
<br/>
v1.044<br/>
Decrease score needs to update game table panel<br/>
Refactored team1/team2 to teams[] in teamController<br/>
Undo needs to repaint gametables<br/>
<br/>
v1.043<br/>
Get Game Table Window displaying properly<br/>
Update Game Table times realtime<br/>
focus back to Code after any button pressed and when window activated<br/>
<br/>
v1.042<br/>
Start on Game Table Window<br/>
Redo for Start Match bug - Run program. Press Start Match button.  Push Undo then press redo.  Throws exception.<br/>
Undo for Start Match bug - Run program. Incrase score team1.  Press Start Match button.  Push Undo. Push Undo again. Throws exception.<br/>
Add ShowParsed to menu bar<br/>
Fix bug in stats.parseCode<br/>
<br/>
v1.041<br/>
Issue #1 Show Invalid stat code in red<br/>
Issue #4 Better Stat History Window (last command always shows)<br/>
<br/>
v1.040<br/>
gametable now is told when games and match are won<br/>
Refactored team1Scored->teamScored[], team1TimeOut->teamTimeOut[]<br/>
Start Game button restarts game timer and advances current game number<br/>
Issue #8 Pause Match button ignores first press after Start Match Button bug<br/>
<br/>
v1.039<br/>
Set alignment and background colors on game table<br/>
<br/>
v1.038<br/>
Game Tracking 5-2,5-3,4-5,3-5,8-7<br/>
Add Start Game button<br/>
Add startGame to hotkeys screen<br/>
Add pauseMatch to hotkeys screen<br/>
<br/>
v1.037<br/>
Issue #5 Switch Sides messes up clear buttons<br/>
Issue #6 Switch Panel clear all button needs to clear reset/warns<br/>
<br/>
v1.036<br/>
Fix xpundo/xpredo - made it xu and xr.<br/>
<br/>
v1.035<br/>
Issue #7 Pause button alternates between Pause/Unpaused Match<br/>
Animate Last Scored Timer Windows<br/>
Start Match now clears history but leaves itself there.<br/>
Switch Sides now switches last scored times<br/>
Add copyright message to Command objects<br/>
<br/>
v1.034<br/>
Issue #7 Match Time Pause/Restart button (Partial. need to fix timers getting out of sync if score increased while paused)<br/>
<br/>
v1.033<br/>
Issue #3 Increase Tournament, Event, Table Name field widths<br/>
<br/>
v1.032<br/>
Issue #2 Handle start up without config file present<br/>
<br/>
v1.03 & v1.031<br/>
Time since last score - each team:<br/>
	a. Create Timers (Done)<br/>
	b. Display times and start/reset on score (Done)<br/>
	c. Write times to files - not wanting to do this - concerns of speed/io issues - but can let filename control whether writes or not (empty - do not write)<br/>
	d. Add filesnames in files settings screen<br/>
	e. Add window for each timer??? (Done)<br/>
Handle Invalid Stat command<br/>
Fix clear stats entry - will error if undo after clear<br/>
Write Time Remaining??? Could be used in place of the timer window but would be very slow.  Leaning towards no on this one. (And yet I did it - but can blank out time remaining file name to prevent writing)<br/>
Closing the timer window needs to uncheck the menuitem TimerWindow or force do nothing on close which is the option I took.<br/>
<br/>
