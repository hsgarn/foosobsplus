# FoosOBSPlus
FoosOBSPlus is a tool to enhance your foosball streaming experience.  It is a flexible foosball score keeper and statistics program that will send the match information to your OBS Studio (Version 28 or higher) streaming software. It also plays well with Elagto's Stream Deck products so updating the score, switching sides, calling times out can all be done with simple button presses.

## Overview
FoosOBSPlus sends your Foosball game data (Players, scores, game counts, time outs, etc.) to OBS Studio using OBS Studio's web sockets protocol.  Almost every field displayed in FoosOBSPlus can be sent to OBS Studio and displayed to your live stream. FoosOBSPlus can also connect to an auto scoring system using a Raspberry Pico W and some lasers (see <https://github.com/hsgarn/FoosScore for more information>).  Also a Stream Deck can be used in combination with Auto Hot Keys to control most functions in FoosOBSPlus.

FoosOBSPlus Main Screen:
<img align="left" width="850" height="630" src="https://github.com/hsgarn/foosOBSPlus/blob/master/foosOBSPlusScreen1.png">

OBS Studio scene utilizing FoosOBSPlus to display various data:
<img align="left" width="1920" height="1090" src="https://github.com/hsgarn/foosOBSPlus/blob/master/foosOBSPlusScreen2.png">

## Setup
FoosOBSPlus is a java program.  You can download the executable jar file from the Releases section. You will need to have at least Java 1.8 loaded and windows set up to associate jar files with java. There are many resources on the web for how to load java and set windows up to run the jar file.  (see <https://www.howtogeek.com/812583/how-to-open-jar-files-windows/> for example.  The executable jar file will run in the directory you placed it. )

## Toolbar Items
## File
### Import Stats
This opens up a window for you to select a stats file to import.  A stats file is a simple text file with one code per line.  The file will be read in and the stats will be displayed in the Statistics Display Panel.  The idea is that you can just write the codes in a text editor as a game is being played and then later import them into FoosOBSPlus for it to generate the stats.

### Export Stats
This option writes the current codes in the Statistics Entry Panel History window to a file so that they can be imported later.  Not sure what the practicality of this is, but the option is here if you ever need it. :)

### Exit
This option can be used to exit the program.  A confirmation window will ask if you really want to exit the application.  Just click OK to exit or Cancel to return to the program.

## Edit
## Settings
If running FoosOBSPlus for the first time, it will create a set of properties files with the default properties in the folder in which the program is running. These property files contain the settings for the operating parameters, sources, filters, hot keys and more.  Below are the options under Settings:

### Parameters
FoosOBSPlus tries to make keeping track of a foosball match's progress as simple as possible.  To that end, there is a settings screen that contains parameters that affect how FoosOBSPlus will behave.  Click Edit then Settings then Parameters to get to the Parameter Settings screen.

<img width="420" height="320" src="https://github.com/hsgarn/foosOBSPlus/blob/master/foosOBSPlusSettings1.png">

Parameter Settings Page:</br>
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
FoosOBSPlus sends most of its data to sources in OBS Studio so it can be displayed in a live stream.  The names of these sources are configurable if the default names do not suit you. The vast majority of these sources should be Text (GDI+) sources with the Read from file checkbox unchecked.  Exceptions to this will be specifically noted below.  There are enough sources that they were split into two windows.  The sources window is described below and the statistics sources window will be described in the following section. To get to the sources configuration, click on Edit, then Settings, then Sources:

<img width="420" height="320" src="https://github.com/hsgarn/foosOBSPlus/blob/master/foosOBSPlusSettings3.png">

Sources Settings Page:</br>
<img width="542" height="442" src="https://github.com/hsgarn/foosOBSPlus/blob/master/foosOBSPlusSettings5.png">

Below are the sources that can be configured:

#### Name: (Team 1, Team 2, Team 3)
A source can be entered for each team's name.  The default source is teamXname where X is the team number (1, 2 or 3).

#### Forward Name: (Team 1, Team 2, Team 3)
A source can be entered for each team's forward.  The default source is teamXforward where X is the team number (1, 2 or 3).

#### Goalie Name: (Team 1, Team 2, Team 3)
A source can be entered for each team's goalie.  The default source is teamXgoalie where X is the team number (1, 2 or 3).

#### Score: (Team 1, Team 2, Team 3)
The score source for each team can be set.  The default source is teamXscore where X is the team number (1, 2 or 3).

#### Game Count: (Team 1, Team 2, Team 3)
The game count source for each team can be set.  The default source is teamXgamecount where X is the team number (1, 2 or 3).

#### Match Count: (Team 1, Team 2, Team 3)
The match count source for each team can be set.  The default source is teamXmatchcount where X is the team number (1, 2 or 3).

#### Time Out: (Team 1, Team 2, Team 3)
The time out count source for each team can be set.  The default source is teamXtimeout where X is the team number (1, 2 or 3).

#### Reset: (Team 1, Team 2, Team 3)
The reset source for each team can be set.  The default source is teamXreset where X is the team number (1, 2 or 3).

#### Warn: (Team 1, Team 2, Team 3)
The warn source for each team can be set.  The default source is teamXwarn where X is the team number (1, 2 or 3).

#### King Seat: (Team 1, Team 2, Team 3)
The king seat source for each team can be set.  The default source is teamXkingseat where X is the team number (1, 2 or 3).

#### Game 1: (Team 1, Team 2, Team 3)
This is the source that will be made visible when team X wins their first game.  This source can be any source type as long as it has the visible property (i.e. Text GUI+, Image, etc.).  The default source is teamXgame1 where X is the team number (1, 2 or 3).

#### Game 2: (Team 1, Team 2, Team 3)
This is the source that will be made visible when team X wins their second game.  Game 1's source will remain visible.  This source can be any source type as long as it has the visible property (i.e. Text GUI+, Image, etc.).  The default source is teamXgame2 where X is the team number (1, 2 or 3).

#### Game 3: (Team 1, Team 2, Team 3)
This is the source that will be made visible when team X wins their third game.  Game 1 and 2 sources will remain visible.  This source can be any source type as long as it has the visible property (i.e. Text GUI+, Image, etc.).  The default source is teamXgame3 where X is the team number (1, 2 or 3).

#### Show Scores:
This is the source that is controlled by the Show Score check box in the OBS Panel on the main screen.  This is intended to be a named group of sources within within OBS or a scene with OBS that contains all the scoring fields and their associated labels.  This group or scene can then be included in your main streaming scene.  When you want to keep score, check the Show Score check box on the main screen.  When no one is keeping the score, uncheck the Show Scores button which will turn the scene/group in the Show Scores: source box off.
The following sources would typically be contained in this scene or group:
Last Scored, Time Out (Teams 1 & 2), Match Count 1 (Teams 1 & 2), Game Count (Teams 1 & 2), Score (Teams 1 & 2), King Seat (Teams 1 & 2).
Not team 3 sources are controlled by Show Cutthroat discussed later.
Also include any labels that would look out of place without the above fields.

#### Show Timer:
This is the source that shows the time remaining.  This should be a Window Capture source in OBS. The window should be setup as follows:</br>
<img width="500" height="200" src="https://github.com/hsgarn/foosOBSPlus/blob/master/foosOBSPlusSettingsTimerWindow.png">

#### Show Cutthroat:
This is the source that is controlled by the Show Cutthroat check box in the OBS Panel on the main screen.  This is intended to be a named group of sources within within OBS or a scene with OBS that contains all the scoring fields and their associated labels for team 3.  This group or scene can then be included in your main streaming scene.  When you want to show team 3's information, check the Show Cutthroat check box on the main screen.  When no one is keeping the score, uncheck the Show Scores button which will turn the scene/group in the Show Scores: source box off.
The following sources would typically be contained in this scene or group:
Time Out (Team 3), Match Count (Team 3), Game Count (Team 3), Score (Team 3), King Seat (Team 3).

#### Tournament:
This is the source of a freeform text field that can be used for the name of the tournament or venue.  Default source is tournament.

#### Event:
This is the source of a freeform text field that can be used for the name of the event being played (i.e. DYP, Open Singles, etc).  Default source is event.

#### Table Name:
This is the source for the table name.  Default source is tablename.

#### Timer:
This is the source that holds the name of the current timer that is running (Shot, Pass, Game, Timeout, Recall). Default source is timerinuse.

#### Time Remaining:
This is the source for the Time Remaining on the current timer.  Default source is timeremaining.  An alternative and arguably better method to show time remaining is to use a Window Capture source in OBS. This will show the background color of the timer which is a nice touch since it starts green and goes red when time runs out.

#### Game Time:
This is the source for the game clock time.  Default source is gametime.

#### Match Time:
This is the source for the match time.  Default source is matchtime.

#### Stream Time:
This is the source for the stream time. Default source is streamtime.

#### Last Scored:
This is the source that holds the indicator for which team scored last.  Default source is lastscored.

#### Match Winner:
This is the source that holds the Winner Prefix, Team's Name and Winner Suffix of the team that won the match.  Default source is matchwinner.

#### Meatball:
This is the source that holds the Meatball text when a game is tied just prior to the final point.  Default source is meatball.

#### Game Results:
This is the source that holds the results of all the games played since the program was started or the Start Event button was pressed.  It is intended to be a Text (GDI+) source with a scrolling filter applied.   Every time a game is won, the current time, team 1 forward & goalie names, team 1 score, team 2 forward & goalie names, team 2 score, and the game time length is added to the text on a new line.  Default source is gameresults.

#### Apply
Click the Apply button to save any source changes made and keep the window open.

#### Apply and Close
Click the Apply and Close button to save any source changes made and close the window.

#### Cancel
Click the cancel button to discard any source changes made.

#### Restore Defaults
Click the Restore Defaults button to restore the default sources.

### Statistics Sources
The statistics fields in FoosOBSPlus can be sent to OBS Studio so it can be displayed in a live stream.  The names of these sources are configurable if the default names do not suit you. The vast majority of these sources should be Text (GDI+) sources with the Read from file checkbox unchecked.  Exceptions to this will be specifically noted below.  To get to the Statistics Sources configuration, click on Edit, then Settings, then Statistics Sources:

<img width="420" height="320" src="https://github.com/hsgarn/foosOBSPlus/blob/master/foosOBSPlusSettings11.png">

Statistics Sources Settings Page:</br>
<img width="542" height="442" src="https://github.com/hsgarn/foosOBSPlus/blob/master/foosOBSPlusSettings12.png">

Below are the sources that can be configured:
(Note: Although Team 3 sources can be defined, currently no statistics are actually captured for Team 3.)
Each field below has a source for Team 1, Team 2 and Team 3.  For the defaults, replace the actual team number for the X.

#### Pass Attempts:
This is the source that holds the number of pass attempts for a team.  Default source is teamXpassattempts.

#### Pass Completes:
This is the source that holds the number of pass completions for a team.  Default source is teamXpasscompletes.

#### Pass Percent:
This is the source that holds the successful passing percentage for a team.  Default source is teamXpasspercent.

#### Shot Attempts:
This is the source that holds the number of shot attempts for a team.  Default source is teamXshotattempts.

#### Shot Completes:
This is the source that holds the number of shots made for a team.  Default source is teamXshotcompletes.

#### Shot Percent:
This is the source that holds the successful shot percentage for a team.  Default source is teamXshotpercent.

#### Clear Attempts:
This is the source that holds the number of clearing attempts for a team.  Default source is teamXclearattempts.

#### Clear Completes:
This is the source that holds the number of successful clears for a team from the goalie area to the 5 bar or beyond.  Default source is teamXclearcompletes.

#### Clear Percent:
This is the source that holds the successful clear percentage for a team.  Default source is teamXclearpercent.

#### 2-Bar Pass Attempts:
This is the source that holds the number of pass attempts for a team from the 2-bar to the 5-bar or 3-bar.  Default source is teamXtwobarpassattempts.

#### 2-Bar Pass Completes:
This is the source that holds the number of successful pass completions for a team from the 2-bar to the 5-bar or 3-bar.  Default source is teamXtwobarpasscompletes.

#### 2-Bar Pass Percent:
This is the source that holds the successful 2-bar to the 5-bar or 3-bar passing percentage for a team.  Default source is teamXtwobarpasspercent.

#### Aces:
This is the source that holds the number of aces for a team. Default source is teamXaces.

#### Stuffs:
This is the source that holds the number of stuffs for a team.  Default source is teamXstuffs.

#### Breaks:
This is the source that holds the number of breaks for a team.  Default source is teamXbreaks.

#### Scoring:
This is the source that holds the number of scores for a team.  Default source is teamXscoring.

#### 3-Bar Scoring:
This is the source that holds the number of scores from the 3-bar for a team.  Default source is teamXthreebarscoring.

#### 5-Bar Scoring:
This is the source that holds the number of scores from the 5-bar for a team.  Default source is teamXfivebarscoring.

#### 2-Bar Scoring:
This is the source that holds the number of scores from the 2-bar for a team.  Default source is teamXtwobarscoring.

#### Shots On Goal:
This is the source that holds the number of shots on goal for a team.  Default source is teamXshotsongoal.

#### Apply
Click the Apply button to save any statistics source changes made.

#### Apply and Close
Click the Apply and Close button to save any statistics source changes made and close the window.

#### Cancel
Click the cancel button to discard any statistics source changes made.

#### Restore Defaults
Click the Restore Defaults button to restore the default statistics sources.

### Hot Keys
FoosOBSPlus uses buttons to do various functions such as increase or decrease scores, switch sides, reset game counts, start timers, etc.  Each button can have a Hot Key assigned to it.  Pressing ALT plus the assigned Hot Key for the button will function just like pressing the actual button.  Unfortunately, there are more buttons than available hot keys so you can not assign a hot key to every button. The hot keys can be used in Stream Deck commands to make operating FoosOBSPlus a simple push button affair.  TouchPortal is another program that can be used to activate the buttons in FoosOBSPlus.  These will require that AutoHotKey be installed. (See <https://www.autohotkey.com/docs/Tutorial.htm>).  FoosOBSPlus will generate the AutoHotKey scripts for any hot keys defined (see the Generate AutoHotKey Scripts button below).

<img  width="420" height="320" src="https://github.com/hsgarn/foosOBSPlus/blob/master/foosOBSPlusSettings4.png">

Hot Key Settings Page:</br>
<img width="552" height="442" src="https://github.com/hsgarn/foosOBSPlus/blob/master/foosOBSPlusSettings6.png">

##### Team 1 Switch Positions
Assigns the hot key to Switch Team 1's forward and goalie names. Default hot key is t.

##### Team 2 Switch Positions
Assigns the hot key to Switch Team 2's forward and goalie names. Default hot key is m.

##### Team 3 Switch Positions
Assigns the hot key to Switch Team 3's forward and goalie names. Default hot key is m.

##### Team 1 Increment Score
Assigns the hot key for the + (increment score) button for Team 1. Default hot key is 1.

##### Team 2 Increment Score
Assigns the hot key for the + (increment score) button for Team 2. Default hot key is 2.

##### Team 3 Increment Score
Assigns the hot key for the + (increment score) button for Team 3. Default hot key is unassigned.

##### Team 1 Decrement Score
Assigns the hot key for the - (decrement score) button for Team 1. Default hot key is 4.

##### Team 2 Decrement Score
Assigns the hot key for the - (decrement score) button for Team 2. Default hot key is 8.

##### Team 3 Decrement Score
Assigns the hot key for the - (decrement score) button for Team 3. Default hot key is unassigned.

##### Team 1 Increment Game
Assigns the hot key for the + (increment game count) button to increase Team 1's game count. Default hot key is 5.

##### Team 2 Increment Game
Assigns the hot key for the + (increment game count) button to increase Team 2's game count. Default hot key is 6.

##### Team 3 Increment Game
Assigns the hot key for the + (increment game count) button to increase Team 3's game count. Default hot key is unassigned.

##### Team 1 Decrement Game
Assigns the hot key for the - (decrement game count) button for Team 1's score. Default hot key is j.

##### Team 2 Decrement Game
Assigns the hot key for the - (decrement game count) button for Team 2's score. Default hot key is i.

##### Team 3 Decrement Game
Assigns the hot key for the - (decrement game count) button for Team 3's score. Default hot key is unassigned.

##### Team 1 Increment Match
Assigns the hot key for the + (increment match) button for Team 1. Default hot key is unassigned.

##### Team 2 Increment Match
Assigns the hot key for the + (increment match) button for Team 2. Default hot key is unassigned2.

##### Team 3 Increment Match
Assigns the hot key for the + (increment match) button for Team 3. Default hot key is unassigned.

##### Team 1 Decrement Match
Assigns the hot key for the - (decrement match) button for Team 1. Default hot key is unassigned.

##### Team 2 Decrement Match
Assigns the hot key for the - (decrement match) button for Team 2. Default hot key is unassigned.

##### Team 3 Decrement Match
Assigns the hot key for the - (decrement match) button for Team 3. Default hot key is unassigned.

##### Team 1 Use Time Out
Assigns the hot key to use a time out for Team 1.  Default hot key is 9.

##### Team 2 Use Time Out
Assigns the hot key to use a time out for Team 2.  Default hot key is 0.

##### Team 3 Use Time Out
Assigns the hot key to use a time out for Team 3.  Default hot key is unassigned.

##### Team 1 Return Time Out
Assigns the hot key to return a time out to Team 1's available pool of timeouts or used pool of time outs depending on how the Show Time Outs Used setting is set.  Default hot key is n.

##### Team 2 Return Time Out
Assigns the hot key to return a time out to Team 2's available pool of timeouts or used pool of time outs depending on how the Show Time Outs Used setting is set.  Default hot key is q.

##### Team 3 Return Time Out
Assigns the hot key to return a time out to Team 3's available pool of timeouts or used pool of time outs depending on how the Show Time Outs Used setting is set.  Default hot key is unassigned.

#### Team 1 Reset
Assigns the hot key for the Reset button for Team 1.  Default hot key is unassigned.

#### Team 2 Reset
Assigns the hot key for the Reset button for Team 2.  Default hot key is unassigned.

#### Team 3 Reset
Assigns the hot key for the Reset button for Team 3.  Default hot key is unassigned.

#### Team 1 Warn
Assigns the hot key for the Warn button for Team 1. Default hot key is unassigned.

#### Team 2 Warn
Assigns the hot key for the Warn button for Team 2. Default hot key is unassigned.

#### Team 3 Warn
Assigns the hot key for the Warn button for Team 3. Default hot key is unassigned.

#### Team 1 King Seat
Assigns the hot key for the King Seat check box for Team 1. Default hot key is unassigned.

#### Team 2 King Seat
Assigns the hot key for the King Seat check box for Team 2. Default hot key is unassigned.

#### Team 3 King Seat
Assigns the hot key for the King Seat check box for Team 3. Default hot key is unassigned.

#### Start Match
Assigns the hot key for the Start Match button.  Default hot key is b.

#### Pause Match
Assigns the hot key for the Pause Match button.  Default hot key is ,.

#### End Match
Assigns the hot key for the End Match button.  Default hot key is unassigned.

#### Start Game
Assigns the hot key for the Start Game button.  Default hot key is ..

##### Shot Timer
Assigns the hot key to start the Shot timer.  Default hot key is s.

##### Pass Timer
Assigns the hot key to start the Pass timer.  Default hot key is p.

##### Time Out Timer
Assigns the hot key to start the Time Out timer.  Default hot key is o.

##### Game Timer
Assigns the hot key to start the Game timer.  Default hot key is g.

##### Recall Timer
Assigns the hot key to start the Recall timer.  Default hot key is c.

##### Reset Timer
Assigns the hot key to reset the timer to 0 regardless of what it is currently timing.  Default hot key is r.

#### Clear All
Assigns the hot key for the Clear All button in the Switch Panel.  Default hot key is unassigned.

#### Undo
Assigns the hot key for the Undo button.  Default hot key is u.

#### Redo
Assigns the hot key for the Redo button.  Default hot key is d.

#### Show Skunk
Assigns the hot key for the Show Skunk button. Default hot key is k.

#### Start Stream
Assigns the hot key for the Start Stream button. Default hot key is z.

#### Clear Tournament
Assigns the hot key for the Clear button for the Tournament Information panel. Default hot key is unassigned.

#### Switch Match Counts
Assigns the hot key for the Switch Match Counts button. Default hot key is unassigned.

#### Switch Sides
Assigns the hot key to swap all Team 1's data with Team 2's data.  Use this when the teams switch sides. Default hot key is w.

##### Switch Teams
Assigns the hot key to swap Team 1's and Team 2's names (Team, Forward & Goalie) with each other.  Default hot key is e.

##### Switch Scores
Assigns the hot key to Switch Team 1 and Team 2's scores.  Default hot key is unassigned.

##### Switch Game Counts
Assigns the hot key to switch the game counts. Team 1's game count will be swapped with Team 2's game count.  Default hot key is unassigned.

##### Switch Time Outs
Assigns the hot key to switch the time out counts. Team 1's time out count will be swapped with Team 2's time out count.  Default hot key is [.

#### Switch Reset/Warns
Assigns the hot key to swap the Reset and Warn flags for Team 1 with Team 2.  Default hot key is unassigned.

#### Switch Forwards
Assigns the hot key for switching only the names of the Forwards of Team 1 and Team 2.  Default hot key is ;.

#### Switch Goalies
Assigns the hot key for switching only the names of the Goalies of Team 1 and Team 2.  Default hot key is x.

#### Reset Names
Assigns the hot key for the Reset Names button in the Reset Panel.  Default hot key is unassigned.

#### Reset Scores
Assigns the hot key for the Reset Scores button in the Reset Panel.  Default hot key is 3.

#### Reset Game Counts
Assigns the hot key to reset Team 1's, Team 2's and Team 3's game counts to 0.  Default hot key is 7.

#### Reset Time Outs
Assigns the hot key to reset Team 1's, Team 2's and Team 3's time outs to 0 or the max time outs depending on how the Show Time Outs Used setting is set.  Default hot key is -.

#### Reset Reset/Warn
Assigns the hot key to clear the Reset and Warn flags for both Team 1 and Team 2.  Default hot key is unassigned.

#### Reset All
Assigns the hot key to clear the game counts, scores, time outs, reset flags and warn flags for both Team 1 and Team 2.  Default hot key is a.

#### Reset Match Counts
Assigns the hot key to reset Team 1's, Team 2's and Team 3's match counts to 0.  Default hot key is 7.

#### AutoHotKey Script Path
This is the path to where the AutoHotKey scripts will be generated.  The default is C:\FoosOBSPlusScripts\.

#### Generate AutoHotKey Scripts
This button will generate an AutoHotKey script for each defined hot key.  The basic idea of the scripts is to bring the FoosOBSPlus window into focus and then press the hot key for the associated hot key for the action desired. It then moves the FoosOBSPlus window to the bottom of the screen so that your OBS program can stay the main focus.  The scripts will be placed into the directory specified in the AutoHotKey Script Path field.

#### Select Path
The Select Path allows you to choose the directory in which the AutoHotKey scripts will be created. Optionally you can just type the path in the box to the right of the Select Path button.

#### Apply
Click the Apply button to save any hot key changes made.

#### Apply and Close
Click the Apply and Close button to save any hot key changes made and close the window.

#### Cancel
Click the cancel button to discard any hot key changes made.

#### Restore Defaults
Click the Restore Defaults button to restore the default hot keys.

### Filters
FoosOBSPlus can trigger OBS Filters when certain events occur.  For instance, when a time out occurs for team 1, a filter can be triggered to show a ref calling time out.  The filter is responsible for turning itself off.    To get to the filters configuration, click on Edit, then Settings, then Filters:

<img width="420" height="320" src="https://github.com/hsgarn/foosOBSPlus/blob/master/foosOBSPlusSettings9.png">

Filters Settings Page:</br>
<img width="552" height="442" src="https://github.com/hsgarn/foosOBSPlus/blob/master/foosOBSPlusSettings10.png">

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

#### Meatball
This is the name of the filter activated when both teams are tied and next ball wins the match.

#### Apply
Click the Apply button to save any filter changes made.

#### Apply and Close
Click the Apply and Close button to save any filter changes made and close the window.

#### Cancel
Click the cancel button to discard any filter changes made.

#### Restore Defaults
Click the Restore Defaults button to restore the default filters.

### Partner Program
FoosOBSPlus can read player names from files.  For example, we have an Access program called Partner Program and when a match is called in this program, it writes the players' names to 4 files. The directory and filenames can be set in the Partner Program Settings window.

<img width="420" height="320" src="https://github.com/hsgarn/foosOBSPlus/blob/master/foosOBSPlusSettings7.png">

Partner Program Settings Page:</br>
<img width="420" height="320" src="https://github.com/hsgarn/foosOBSPlus/blob/master/foosOBSPlusSettings8.png">

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

#### Apply
Click the Apply button to save any changes made.

#### Apply and Close
Click the Apply and Close button to save any changes made and close the window.

#### Cancel
Click the cancel button to discard any changes made.

#### Restore Defaults
Click the Restore Defaults button to restore the default file names.

## OBS
This is the menu item for OBS Studio related activity.  A green solid circle will show before OBS to indicate that a connection has been established.  A red solid circle indicates that OBS Studio is currently disconnected.

### Connect...
This opens the OBS Connect window.  Here you can set the connection details for your OBS instance along with a few options.

<img width="420" height="320" src="https://github.com/hsgarn/foosOBSPlus/blob/master/foosOBSPlusOBSConnect.png">

#### Host
This is the host name or ip address of the computer running OBS Studio. FoosOBSPlus must be running on the same network as OBS Studio.  If they are running on the same computer, then you can use localhost as the host name.  OBS Studio will provide the connection details by going to Tools -> obs-websocket Settings -> Show Connect Info.

#### Port
This is the port that OBS Studio is listening too for connections.  OBS Studio will provide the connection details by going to Tools -> obs-websocket Settings -> Show Connect Info.

#### Password
This is the password for OBS Studio.  OBS Studio will provide the connection details by going to Tools -> obs-websocket Settings -> Show Connect Info.

#### Scene
THis is the name of the scene in OBS Studio that FoosOBSPlus will be sending it's data to.

#### Set Scene
Press the Set Scene button to get OBS Studio to show the scene in the Scene box.

#### Save Password
Check this box if you want FoosOBSPlus to save the password when it exits.  Otherwise, you will have to enter each time you run FoosOBSPlus and wnat to connect to OBS Studio.

#### Auto Login on Start
If this box is checked, then when FoosOBSPlus first starts, it will try to connect to OBS Studio using the details saved in the OBS Connect window.

#### Close on Connect
If this box is checked, then the OBS Connect window will close when FoosOBSPlus successfully connects to OBS Studio.

#### Update on Connect
If this box is checked, then when FoosOBSPlus connects to OBS Studio, it will immediately send the current data in FoosOBSPlus to OBS Studio. Otherwise, data will only be sent when fields change in FoosOBSPlus.

#### Connect
This button attempts to connect FoosOBSPlus to OBS Studio.  The Host, Port and Password fields must be set correctly and OBS Studio must be running on the same network for a successful connection. The button will be grayed out if already FoosOBSPlus is already connected to OBS Studio.

#### Disconnect
This button will disconnect FoosOBSPlus from OBS Studio.  This button will be grayed out if FoosOBSPlus is already disconnected from OBS Studio.

#### Save
This button will save the current settings in the OBS Connect window.

#### Message
This window will show any messages relevant to communicating with OBS Studio.

### Disconnect
Click this menu item to disconnect FoosOBSPlus from OBS Studio.

## AutoScore
This is the menu item for FoosScore AutoScore related activity.  A green solid circle will show before AutoScore to indicate that a connection has been established to the FoosScore AutoScore system.  A red solid circle indicates that FoosScore AutoScore is currently disconnected.

<img width="420" height="320" src="https://github.com/hsgarn/foosOBSPlus/blob/master/foosOBSPlusAutoScoreSettings.png">

## Settings

### Server Address
This is the IP Address of the FoosScore AutoScore server.  The AutoScore server will need to be on the same network as FoosOBSPlus in order to successfully communicate with FoosOBSPlus.
### Server Port
This is the port number that the FoosScore AutoScore server is listening to.
### Auto Connect on Start Up
When checked, FoosOBSPlus will attempt to automatically connect to the FoosScore AutoScore system when FoosOBSPlus if first started.  The Server Address and Server Port fields should be populated for Auto Connect to work. If unchecked, you must manually connect using the Connect button.
### Detail Log
When checked, details sent from the FoosScore AutoScore system can be seen in the Message window.
### Connect
This button will connect to the FoosScore AutoScore system at the given Server Address and Server Port.
### Disconnect
This button will disconnect the current active FoosScore AutoScore session.
### Message
This box shows communication and information for the FoosScore AutoScore system.
### Apply and Close
This button will save the current settings in the AutoScore Settings window.  New settings will not take affect until this button is pressed.
### Cancel
This button will close the AutoScore Settings window without permanently saving any changes.  If you reopen the window, you may see your changes still there, but they will not be permanently stored until the Save button is pressed.
### Restore Defaults
This button will restore the AutoScore Settings defaults.  The Save button must be pressed to make these changes permanent.

<img width="420" height="320" src="https://github.com/hsgarn/foosOBSPlus/blob/master/foosOBSPlusAutoScoreConfig.png">

## Configuration

### AutoScore Configuration:
This window is where you can edit the FoosScore AutoScore configuration.  Typically you would press the Read Configuration to see the current configuration.  Then make any changes desired.  THen press the Validate Configuration button to verify changes are acceptable. Then press Write Configuration to send the changes to the FoosScore AutoScore system (Raspberry Pico).  Lastly, press Reset Pico for the changes to take affect. You will have to reconnect after the Pico resets.

These are the following parameters that can be changed:</br></br>
PORT:  This is the port that the FoosScore AutoScore system will communicate on.</br>
SENSOR1:  This is the Pico GPIO Pin number for sensor 1.  Note, this should only be changed if the sensor is physically moved to another pin.</br>
SENSOR2:  This is the Pico GPIO Pin number for sensor 2.  Note, this should only be changed if the sensor is physically moved to another pin.</br>
SENSOR3:  This is the Pico GPIO Pin number for sensor 3.  Note, this should only be changed if the sensor is physically moved to another pin.</br>
LED1:  This is the Pico GPIO Pin number for LED 1.  Note, this should only be changed if the LED is physically moved to another pin.</br>
LED2:  This is the Pico GPIO Pin number for LED 2.  Note, this should only be changed if the LED is physically moved to another pin.</br>
DELAY_TIME:  This is the amount of time in milliseconds that the FoosScore AutoScore will ignore the sensors after a sensor has been triggered.  If this time is too short, the ball could bounce around and hit the sensors again causing an extra point to be sent.  If this time is too long, then two quick points back to back may only count once.  5000 milliseconds (5 seconds) has served us well so far.</br>
### Read Configuration
This button retrieves the current FoosScore AutoScore configuration from the Raspberry Pico device and displays it in the AutoScore Configuration window.

<img width="420" height="320" src="https://github.com/hsgarn/foosOBSPlus/blob/master/foosOBSPlusAutoScoreConfig2.png">

### Clear Configuration
This button will clear the AutoScore Configuration window.
### Validate Configuration
This button will check each line of the configuration in the AutoScore Configuration window. Any errors will be reported and will prevent you from saving the bad configuration.
### Write Configuration
This button will first validate the configuration. If validated successfully, then it will send the configuration data to the FoosScore AutoScore system.  The new configuration will take affect the next time the FoosScore AutoScore system is restarted.  (This can be done by the Reset Pico button or by turning the Pico off and back on again.)
### Reset Pico
This will cause the FoosScore AutoScore system to restart allowing any configuration changes to take affect.

## View

<img width="420" height="320" src="https://github.com/hsgarn/foosOBSPlus/blob/master/foosOBSPlusViewMenu.png">

### Always on Top
When checked, the main FoosOBSPlus program window will stay on top of other programs.
### Timer Window
When checked, the main timer window is displayed.  This is a window that can be used in OBS to display the time of the current timer running (shot, pass, time out, game, recall).  In OBS create a Window Capture source and set Window to "[javaw.exe]:FoosOBSPlus Timer Window", Capture Method to Automatic and  WIndow Match Priority to "Window title must match".
### Team 1 Last Scored Window
When checked, the Team 1 Last Scored Timer Window will display.  This shows how long it has been since Team 1 has scored.  To show in OBS, create a Window Capture source and set Window to "[javaw.exe]:FoosOBSPlus Team 1 Last Scored Timer Window", Capture Method to Automatic and  WIndow Match Priority to "Window title must match".w
### Team 2 Last Scored Window
When checked, the Team 2 Last Scored Timer Window will display.  This shows how long it has been since Team 2 has scored.  To show in OBS, create a Window Capture source and set Window to "[javaw.exe]:FoosOBSPlus Team 2 Last Scored Timer Window", Capture Method to Automatic and  Window Match Priority to "Window title must match".
### Team 3 Last Scored Window
When checked, the Team 3 Last Scored Timer Window will display.  This shows how long it has been since Team 3 has scored.  To show in OBS, create a Window Capture source and set Window to "[javaw.exe]:FoosOBSPlus Team 3 Last Scored Timer Window", Capture Method to Automatic and  Window Match Priority to "Window title must match".
### Game Table Window
When checked, the Game Table Window will display.  This shows the games for the current match being played. To show in OBS, create a Window Capture source and set Window to "[javaw.exe]:FoosOBSPlus Game Table Window", Capture Method to Automatic and  Window Match Priority to "Window title must match".
### Game Results Window
When checked, the Game Results Window will display showing all the game results since the program started. The contents of the Game Results window are pushed to a Text (GDI+) source designated in Source Settings under Game Results.

<img width="420" height="320" src="https://github.com/hsgarn/foosOBSPlus/blob/master/foosOBSPlusGameResultsWindow.png">

Each game result has a check box before it that will control whether that line will be pushed or not.  The Clear button will uncheck all the games and will clear the source in OBS.  The Update button will send any checked games to OBS.
### Show All Windows
When checked, all the above windows will display.  Unchecking the box will close all the above windows.

<img width="420" height="320" src="https://github.com/hsgarn/foosOBSPlus/blob/master/foosOBSPlusAllWindows.png">

## Help
### FoosOBSPlus Help
Clicking this entry will take you to the FoosOBSPlus Github page containing this README.md file.
### Show Parsed
When checked, more detailed data is shown in some cases. This is mainly for debugging and since logging has been added, this may go away in the future.
### About
Shows details about the current version of the program.

## FoosOBSPlus Main Screen
The FoosOBSPlus Main Screen is divided into 11 panels.  Each panel has its own controls and purpose.  Below is a description of each panel.

### Tournament Information
This panel contains information pertaining to the particular tournament being played.  Tournament name, event name and table name can be displayed.

<img width="320" height="270" src="https://github.com/hsgarn/foosOBSPlus/blob/master/foosOBSPlusTournamentInformation.png">

#### Tournament Name
This field will be sent to the source specified in the Tournament field in Sources Settings. Use this to display the name of the tournament or maybe the host venue's name.
#### Event Name
This field will be sent to the source specified in the Event field in Sources Settings. Use this to display the event currently being played (i.e. DYP, Singles, etc.).
#### Table Name
This field will be sent to the source specified in the Table Name field in Sources Settings.  Use this to display the table name (or number) that is currently being streamed.
#### Clear
This button will clear all the fields in the Table Information panel.

### Match Information
This panel provides a way to manage a match and shows a game table grid of the current match being played.

<img width="420" height="320" src="https://github.com/hsgarn/foosOBSPlus/blob/master/foosOBSPlusMatchInformation.png">

#### Start Event
This button drops a Start Event marker in the streamindex.txt file if Start Stream is activated.  It will also reset the Game Results variable used to send the Game Results to OBS Studio through the Game Results source.
#### Start Match
This button will reset all scores, game counts, time outs, reset/warns and times.  It also records the current time as the Start Time and will drop a Start Match marker in the streamindex.txt file if Start Stream is activated.
#### Pause Match
This button will pause the match time shown in the Elapsed Time field as well as the Game Time and Last Score Timers.  Once pressed, it will change to Unpause Match and pressing again will resume the timers.  This could be useful if something interrupts the event. But frankly, I have never used it.
#### End Match
This button stops the match, game, and last score timers. It will also drop an End Match marker in the streamindex.txt file if Start Stream is activated.
#### Start Game
This button will reset the Game Time and will unpause the Elapsed Time if the Pause Match button was pressed.  It will also drop a Start Game marker in the streamindex.txt file if Start Stream is activated.
It does not reset the last scored timers which could be a bug or a feature.
#### Start Time
This displays the time when the Start Match button was pressed.
#### Elapsed Time
This displays the running time for the current match. It starts when the Start Match button is pressed and can be paused by the Pause Match button. The End Match button will stop the elapsed time as well.  It will also automatically stop when the program detects that a match has been won by one team or the other.
#### Game Time
This display the running time of the current game. The Pause Match button will pause/unpause the Game Time.  Reaching the points for winning a game will restart the game timer for the next game.  If the last game of the match is over, then the game timer will stop and have to be restarted by the Start Match or Start Game buttons.
#### Game Table
The Game Table shows the scores and time elapsed for each game.  Game winning score is highlighted in blue and match winning team is highlighted in Green.  The current game number is highlighted in gray.  The game table will only display up to 11 games and anything over 5 games won't look pretty.
### Team 1 Information & Team 2 Information & Team 3 Information
These panels manage the information specific to Team 1, Team 2 and Team 3 for the current match.

<img width="520" height="320" src="https://github.com/hsgarn/foosOBSPlus/blob/master/foosOBSPlusTeamInformation.png">

#### Team Name
This field will be sent to the source specified in the Team 1 Name field in Sources Settings. Use this to display the team name for Team 1.
#### Forward Name
This field will be sent to the source specified in the Team 1 Forward Name field in Sources Settings. Use this to display the name of the forward for Team 1.
#### Goalie Name
This field will be sent to the source specified in the Team 1 Goalie Name field in Sources Settings. Use this to display the name of the goalie for Team 1.
#### Switch Positions
The Switch Positions button will swap the Forward's and Goalie's names. Use this when Team 1 switches positions.
#### Score
This field is an editable field that will allow you to change the score for Team 1.  The score is sent to the source specified in the Team 1 Score field in Sources Settings.
#### Score -
This button will decrement Team 1's score by 1.  It can be assigned a hot key in Hot Keys Settings.
#### Score +
This button will increment Team 1's score by 1.  It can be assigned a hot key in Hot Keys Settings.
#### Game Count
This field is an editable field that will allow you to change the game count for Team 1.  The game count is sent to the source specified in the Team 1 Game Count field in Sources Settings.
#### Game Count -
This button will decrement Team 1's game count by 1.  It can be assigned a hot key in Hot Keys Settings.
#### Game Count +
This button will increment Team 1's game count by 1.  It can be assigned a hot key in Hot Keys Settings.
#### Match Count
This field is an editable field that will allow you to change the match count for Team 1.  The match count is sent to the source specified in the Team 1 Match Count field in Sources Settings.
#### Match Count -
This button will decrement Team 1's match count by 1.  It can be assigned a hot key in Hot Keys Settings.
#### Match Count +
This button will increment Team 1's match count by 1.  It can be assigned a hot key in Hot Keys Settings.
#### Time Out Count
This field is an editable field that will allow you to change the time outs for Team 1.  The time out count is sent to the source specified in the Team 1 Time Out field in Sources Settings.  This field can behave in two different ways depending on whether the Show Time Outs Used checkbox is checked or not in Parameters Settings. If checked, then the number in the Time Out Count field shows the number of time outs used. Otherwise, it shows the number of time outs remaining.
#### Return TO
This button will return a time out to Team 1.  This will either increment or decrement the Time Out Count depending on how the Show Time Outs Used checkbox in Parameters Settings is set. It can be assigned a hot key in Hot Keys Settings.
#### Use TO
This button will charge a time out to Team 1.  This will either increment or decrement the Time Out Count depending on how the Show Time Outs Used checkbox in Parameters Settings is set. It can be assigned a hot key in Hot Keys Settings.
#### Reset
This button will send the word RESET to the source specified in the Team 1 Reset field in Sources Settings.  It is a toggle button so when pressed once, it displays RESET. When pressed again, it clears the text.  A hot key can be assigned to this button in Hot Keys Settings.
#### Warn
This button will send the word WARNING to the source specified in the Team 1 Warn field in Sources Settings.  It is a toggle button so when pressed once, it displays WARNING. When pressed again, it clears the text.  A hot key can be assigned to this button in Hot Keys Settings.
#### Time Since Last Scored
This is the time elapsed since Team 1 scored a goal. It is reset by the Start Match button or when Team 1 scores a point.
#### King Seat
This checkbox is used to flag the team as currently being in the King Seat (top of the winners bracket).  When checked, a crown (♔) will be displayed. Since only 1 team can be designated as in the King Seat, the other teams' King Seat checkboxes will be cleared.  Clearing the checkbox will clear the text.
### Team 2 Information
This panel is identical to Team 1 Information panel but is for tracking Team 2's information.
### Team 3 Information
This panel is identical to Team 1 Information panel but is for tracking Team 3's information.
### Timer Panel
The Timer Panel gives the ability to start and reset various timers.  Only one time is active at a time

<img width="420" height="320" src="https://github.com/hsgarn/foosOBSPlus/blob/master/foosOBSPlusTimerPanel.png">

#### Active Timer
The first line in the Timer Window displays which timer is currently active. This will be one of the following:</br></br>
Timer Reset - shows when no timer is currently active.</br>
Shot Timer</br>
Pass Timer</br>
Time Out Timer</br>
Game Timer</br>
Recall Timer
#### Shot Timer Start
This button starts the Shot Timer.  The amount of time allowed for a shot is defined in the Shot Time field in Parameters Settings.  A hot key can be assigned to this button in Hot Keys Settings.
#### Pass Timer Start
This button starts the Pass Timer.  The amount of time allowed for a pass is defined in the Pass Time field in Parameters Settings.  A hot key can be assigned to this button in Hot Keys Settings.
#### Time Out Timer Start
This button starts the Time Out Timer.  The amount of time allowed for a time out is defined in the Time Out Time field in Parameters Settings.  A hot key can be assigned to this button in Hot Keys Settings.
#### Game Timer Start
This button starts the Game Timer.  The amount of time allowed between games is defined in the Game Time field in Parameters Settings.  A hot key can be assigned to this button in Hot Keys Settings.
#### Recall Timer Start
This button starts the Recall Timer.  The amount of time allowed for a recall is defined in the Recall Time field in Parameters Settings.  A hot key can be assigned to this button in Hot Keys Settings.
#### Reset Timer
This button will reset the timer.  This is useful when the ball jumps off the table.  A hot key can be assigned to this button in Hot Keys Settings.
### OBS Panel
The OBS Panel provides some controls to manage OBS.  OBS must be configured in the Connect item in the OBS menu for any of the OBS Panel buttons to work.

<img width="420" height="320" src="https://github.com/hsgarn/foosOBSPlus/blob/master/foosOBSPlusOBSPanel.png">

#### Connect
This button will connect FoosOBSPlus to OBS. Once connected, data will automatically be sent to OBS when changed in FoosOBSPlus. 
#### Update OBS
This button will send the current data in FoosOBSPlus to OBS.  This can be useful if you have to restart OBS and reconnect.
#### Show Scores
This checkbox will send a command to OBS to show or hide the source identified in the Show Scores field in Sources Settings depending on if the box is checked (show) or not (hide). It is useful to hide the scores in OBS when no one is keeping score and to show them again when someone is available or AutoScore is working.
#### Show Timer
This checkbox will send a command to OBS to show or hide the source identified in the Show Timer field in Sources Settings depending on if the box is checked (show) or not (hide).  It is useful to hide the timer window in OBS when no one is available to run the timers.
#### Disconnect
This button will disconnect OBS.
#### Fetch OBS
This button will read the data in OBS and show it in FoosOBSPlus.
#### Enable Skunk
This checkbox will enable activating the Skunk filters identified in Team 1 Skunks and Team 2 Skunks in Filters Settings.  The Skunk filters will only be activated when the Enable Skunk checkbox is checked.
#### Start Stream Timer/Stop Stream Timer
This button will start a stream timer and create a file called streamindex.txt.  Markers can be dropped in this file to show the time stamps when certain events happen.  This can be useful for indexing the stream videos.
Once the Start Stream Timer button is pressed, it will toggle to Stop Stream Timer button.  This will then stop the stream timer. The next press will reset the time and start it again.
#### Stream Time:
The time to the right of the Start Stream Timer/Stop Stream Timer button shows the running stream time when the Start Stream Timer button has been pressed.
### AutoScore Panel
The AutoScore Panel controls the interaction with the AutoScore system.  This is a home grown system using lasers and a Raspberry Pico to detect when the ball is scored in one goal or the other.  When a score is detected, it sends data to FoosOBSPlus to increment the scoring team's score by one point.

<img width="320" height="220" src="https://github.com/hsgarn/foosOBSPlus/blob/master/foosOBSPlusAutoScorePanel.png">

#### Connect
This button will connect to the AutoScore system.  The AutoScore system must already be configured in the AutoScore Settings for the connection to work.
#### Settings
This button will bring up the AutoScore Settings window where the Server Address and Port can be configured.
#### Disconnect
This button will disconnect from the AutoScore system.
#### Ignore Sensors
Check this box if there is a need to temporarily ignore the input from the AutoScore system.  Uncheck it to resume letting the AutoScore system update the scores.
### Switch Panel
The Switch Panel is used to swap the information between Team 1 and Team 2.

<img width="320" height="370" src="https://github.com/hsgarn/foosOBSPlus/blob/master/foosOBSPlusSwitchPanel.png">

#### Switch Sides
The Switch Sides button is used when the teams switch sides.  The team names, player names, scores, time outs, game counts, match counts, reset/warns, king seat and last scored times will all be swapped. A hot key can be assigned to this button in Hot Keys Settings.
#### Switch Teams
This button will swap the Team Name, Forward Name and Goalie Name of Team 1 and Team 2. A hot key can be assigned to this button in Hot Keys Settings.
#### Switch Forward
This button will only swap Forward Names of Team 1 with Team 2. A hot key can be assigned to this button in Hot Keys Settings.
#### Switch Goalie
This button will only swap Goalie Names of Team 1 with Team 2. A hot key can be assigned to this button in Hot Keys Settings.
#### Switch Scores
This button will swap the scores of Team 1 with Team 2. A hot key can be assigned to this button in Hot Keys Settings.
#### Switch Game Counts
This button will swap the game counts of Team 1 with Team 2. A hot key can be assigned to this button in Hot Keys Settings.
#### Switch Match Counts
This button will swap the match counts of Team 1 with Team 2. A hot key can be assigned to this button in Hot Keys Settings.
#### Switch Time Outs
This button will swap the Time Out Counts of Team 1 with Team 2. A hot key can be assigned to this button in Hot Keys Settings.
#### Switch Reset/Warns
This button will swap the Reset and Warn states of Team 1 with Team 2.  A hot key can be assigned to this button in Hot Keys Settings.
#### Clear All
This button will clear all of Team 1 and Team 2's information.
#### Last Scored
This is a display only field that shows which team scored last.  An arrow will point to which team scored last. If no arrow, then no team has scored yet.
### Reset Panel
The Reset Panel is useful for reseting information in the Team 1 and Team 2 Information panels.

<img width="270" height="320" src="https://github.com/hsgarn/foosOBSPlus/blob/master/foosOBSPlusResetPanel.png">

#### Reset Names
This button will reset the Team Name, Forward Name and Goalie Name for all teams. A hot key can be assigned to this button in Hot Keys Settings.
#### Reset Scores
This button will set the Scores for all teams to 0. A hot key can be assigned to this button in Hot Keys Settings.
#### Reset Game Counts
This button will set the Game Counts for all teams to 0. A hot key can be assigned to this button in Hot Keys Settings.
#### Reset Match Counts
This button will set the Match Counts for all teams to 0. A hot key can be assigned to this button in Hot Keys Settings.
#### Reset Time Outs
This button will reset the Time Out Counts for all teams. A hot key can be assigned to this button in Hot Keys Settings.
#### Reset Reset/Warns
This button will clear the Reset and Warn buttons for all teams. A hot key can be assigned to this button in Hot Keys Settings.
#### Reset All
This button will clear the Scores, Game Counts, Match Counts, Time Out Counts, Resets and Warns and King Seat for all teams.  Note that the Name fields are not reset.  A hot key can be assigned to this button in Hot Keys Settings.
### Statistics Entry Panel
The Statistics Entry Panel is intended to be used for entering codes to track statistics during the foosball match.

<img width="320" height="320" src="https://github.com/hsgarn/foosOBSPlus/blob/master/foosOBSPlusStatisticsEntryPanel.png">

#### Foosball Code
Enter codes here.  Most of the buttons on FoosOBSPlus main screen have codes that can be entered that act the same as pressing the button itself.  These are actually commands and are preceeded with an X.  Other codes can be entered to track the ball in the foosball match. These codes are then converted into stats that will be shown in the Statistics Display Panel.  The commands and codes are explained in the Codes and Commands section.
#### History
All commands and codes entered are shown in the History area.  The last command/code entered is shown at the top of the box. Clicking inside the History box and pressing Control-A followed by Control-C will allow you to copy all the current commands in the History area. It will not show highlighted, but it will copy them to the clipboard.
#### Clear
This button will clear the History area.
#### Undo
This button will undo the last command or code entered.
#### Redo
This button will redo the last command or code that was undone.
### Statistics Display Panel
The Statistics Display Panel displays any statistics generated for the current match.  The Team Names (if any) are shown at the top of the panel.  Followed by the Forward and Goalie names for Teams 1 and 2.  Statistics for team 1 are shown on the left and statistics for team 2 are shown on the right. A description of each statistic is in the middle.  Double clicking with the left mouse button anywhere in the Statistics Display Panel will copy the current statistics to the clipboard.

<img width="320" height="370" src="https://github.com/hsgarn/foosOBSPlus/blob/master/foosOBSPlusStatisticsDisplayPanel.png">

For Passing, Shooting, Clearing and 2-Bar Passing, there are 6 numbers. 3 for each team. The 3 numbers on the left are for team 1 and the 3 numbers on right are for team 2.  On the left the order is success, attempts, percentage. On the right, the order is percentage, success, attempts.
#### Passing
The first value is the number of successful passes (5 man to 3 man). The second number is the number of pass attempts.  The third number is the pass success percentage.
#### Shooting
The first value is the number of successful shots. The second number is the number of shot attempts.  The third number is the shot success percentage.
#### Clearing
The first value is the number of successful clears. The second number is the number of clear attempts.  The third number is the clearing success percentage.
#### 2-Bar Passing
The first value is the number of successful passes (2 man to 3 man). The second number is the number of pass attempts.  The third number is the pass success percentage.
#### Shots On Goal
This is the number of shots on goalie from the goalie position.
#### Scoring
This is the total number of scores.
#### 3-Bar
This is the number of scores made from the 3 bar.
#### 5-Bar
This is the number of scores made from the 5 bar.
#### 2-Bar
This is the number of scores made from the goalie position.
#### Breaks
This is the number of breaks.
#### Stuffs
This is the number of stuffs.
#### Aces
This is the number of aces.

## Codes and Commands
The following code scheme will allow you to record every possession in a game by just learning what each position in the code designates and learning only a handful of codes.  Using these codes, the score and timers can be automatically maintained throughout the match.  Each position in the code has from 2 to 10 possible mostly intuitive values. Below is the breakdown of the code.

1st position - team: which team has the ball.</br>
y - yellow team</br>
b - black team</br>
x - command trigger (see below for list of commands)</br>

2nd position - rod: where is the ball:</br>
2 - 2 bar or 3 bar goalie rod.  These rods are treated as one rod.</br>
3 - 3 bar forward rod.</br>
5 - 5 bar forward rod.</br>
g - goal (someone scored)</br>
o - off table</br>
d - dead ball</br>

3rd position - action: how did the ball get here:</br>
a - ace (used when either 5 bar shot in goal from serve, or passed to the 3 from serve and shot from the 3 without any opponent intervention and without missing both the pass and the shot)</br>
c - clear (from goalie 2 or 3 rod to past the same team's 5 row)</br>
p - pass</br>
s - shot</br>
d - drop (serving ball after a score or being knocked off the table or after time out called)</br>
t - time out</br>
r - reset</br>
w - reset warning</br>
x - penalty (see 4th position for penalty modifiers)</br>
y - ball placement after a technical foul shot has been taken</br>
e - error (turnover)</br>

4th position - detail: provides more detail for the action if needed.</br>
b - break - used with pass or shot to designate that the attempt was successful, but only because of a lucky break</br>
f - stuff - used with shot or pass to indicate shot/pass was stuffed into shooters/passers goal (y3p, ygsf)</br>
w - 3 walls 5 bar violation - used with 3rd position set to x</br>
s - spin violation - used with 3rd position set to x</br>
j - jarring violation - used with 3rd position set to x</br>
d - distraction - used with 3rd position set to x</br>
t - too many time outs - used with 3rd position set to x</br>
p - illegal pass - used with 3rd position set to x</br>
x - technical foul - used with 3rd position set to x</br>
r - ready protocol violation - used with 3rd position set to x</br>
o - other violation - future rules or ones just overlooked - used with 3rd position set to </br>


### Examples (this is laid out in the form of an actual game taking place):
y5d - ball is on the 5 bar of the yellow team who "has the drop" either due to winning the coin toss, ball off the table, or oppenent scored.  y5d or b5d will always be how a new point, game, and match start out.
y3p - ball is passed to the 3 bar
bgs - ball shot into black team's goal (score y=1, b=0)
b5d - now black team has ball on the 5 from the drop.
y5xw - ball on yellow 5 man because black team hit 3 walls on their 5 bar possession.
y3p - ball successfully passed from 5 bar to 3 bar.
y2s - ball was shot, but missed and landed on yellow 2 bar.
y5p - ball passed to yellow 5 bar successfully.
ygp - yellow tried to pass to 3 but was blocked into their own goal (score y=1, b=1)
y5d - yellow has ball on 5 bar after black scored.
y5t - yellow called time out, ball still on the 5 bar
y5d - yellow starts play again from 5 bar
y3p - pass to 3 bar successful
y3r - black blocking agressively and reset is called
bgs - ball shot into black goal (score y=2, b=1)

### Commands: (These are just like hitting the corresponding buttons in the UI)
* xu - push undo button
* xr - push redo button
* xpsm - push Start Match button
* xppm - push Pause Match button
* xpsg - push Start Game button
* xsst - start shot timer
* xspt - start pass timer
* xsgt - start game timer
* xstt - start timeout timer
* xsrt - start recall timer
* xprt - push reset timer
* xist1 - increase score team 1
* xist2 - increase score team 2
* xist32 - increase score team 3
* xdst1 - decrease score team 1
* xdst2 - decrease score team 2
* xdst3 - decrease score team 3
* xigt1 - increase game count team 1
* xigt2 - increase game count team 2
* xigt3 - increase game count team 3
* xdgt1 - decrease game count team 1
* xdgt2 - decrease game count team 2
* xdgt3 - decrease game count team 3
* ximt1 - increase match count team 1
* ximt2 - increase match count team 2
* ximt3 - increase match count team 3
* xdmt1 - decrease match count team 1
* xdmt2 - decrease match count team 2
* xdmt3 - decrease match count team 3
* xutt1 - use timeout for team 1
* xutt2 - use timeout for team 2
* xutt3 - use timeout for team 3
* xrtt1 - return timeout for team 1
* xrtt2 - return timeout for team 2
* xrtt3 - return timeout for team 3
* xprt1 - push reset button for team 1
* xprt2 - push reset button for team 2
* xprt3 - push reset button for team 3
* xpwt1 - push warn button for team 1
* xpwt2 - push warn button for team 2
* xpwt3 - push warn button for team 3
* xxpt1 - switch positions team 1
* xxpt2 - switch positions team 2
* xxpt3 - switch positions team 3
* xpss - push switch sides button
* xpst	- switch teams
* xxp1 - switch player 1 (forward)
* xxp2 - switch player 2 (goalie)
* xpssc	- switch scores
* xpsgc	- switch game counts
* xpsto	- switch time outs
* xpsr	- switch reset/warns
* xpca	- clear all (switch)
* xprn - push reset names
* xprs - push reset scores
* xprg - push reset game counts
* xprto - push reset time outs
* xprr - push reset reset/warns
* xpra - push reset all
* xpso - push sync obs button
* xpnb - push 9 ball button
* xpsa - push show all button
* xpha - push hide all button
* xptca - push tournament clear all button
* xpkt1 - push king seat team 1
* xpkt2 - push king seat team 2
* xpkt3 - push king seat team 3

# Found this useful? Consider donating a refreshing beverage to me :)</br>
As you can see by the revision history below, I have spent many hours working on this software.  It has grown from a simple score keeper application to a fairly sophisticated application able to interface with an Auto Scoring system and OBS Streaming software. I have licensed the software so it is free to use and modify as you see fit.  The source code is freely available.  However, if you find the software helpful in any way and would like to buy me a refreshing beverage or a foosball, please feel free to make a donation here:  
[![Donate](https://www.paypalobjects.com/en_US/i/btn/btn_donate_SM.gif)](https://www.paypal.com/donate/?business=MQLATTDXA7CPJ&no_recurring=0&currency_code=USD)

## Revision History</br>
v2.046 02/04/2024</br>
Add cutthroat output for Game Results Window.</br>
Refactor Stats class.</br>
Show game start time and duration in Game Results Window.</br>
</br>
v2.045 02/04/2024</br>
Add JavaDoc to Command classes.</br>
Make Main more Static and stop passing Main where can be used statically.</br>
Refactor FoosOGBSPlusApp.</br>
Increase match count in cutthroat mode when match won.</br>
</br>
v2.044 01/27/2024</br>
Add Apply button and fix Apply and Close button in AutoScoreSettings window.</br>
Add Game Results Window and allow control over displaying each line.</br>
</br>
v2.043 01/16/2024</br>
Add link to ITSF Rules in Help menu.</br>
Change Save buttons to Apply and Close globally.</br>
Add Apply button to Parameters, HotKey, Source, Stats Source, Filters & Partner Program Panels.</br>
Cancel button now returns settings displayed to last saved stated.</br>
Fix bugs in HotKeyPanel resulting from typos in refactoring.</br>
Fix bug where Enable Skunk and StartStream hotkeys would not register change until program restarted.</br>
Fix HotKeyPanel path bug.</br>
Add Available HotKeys field to HotKeysPanel.</br>
</br>
v2.042 01/14/2024</br>
More Settings refactoring.</br>
</br>
v2.041 01/13/2024</br>
Add Meatball Filter to enable filter when match is tied and next point wins.</br>
Refactor getDefaultFilters in Settings.</br>
</br>
v2.040 01/07/2024</br>
Modify Copyright to include the fancy copyright symbol ©. </br>
Make default properties final in Settings.</br>
Handle IOException in Settings.</br>
Added LICENSE.txt file on previous commit (but after v2.039 commit).</br>
</br>
v2.039 01/07/2024</br>
Inserted and then commented out code to getSceneItemList and getGroupSceneItemList in the main OnReady method.</br>
Add donation section to README.md and make proper hyperlinks for the HTTPS references.</br>
Allow sources to contain scenename,sourcename to override currently set scene. This allows nested scenes to work for showSource() method.</br>
Create a CurrentProgramSceneChanged event listener. Set OBS.currentScene.</br>
Refactor checkItemEnableStageChange() in main to reduce duplicated code.</br>
Use OBS.getCurrentScene instead of calling scene from OBS.</br>
Made Settings class final static.</br>
Refactored Settings methods to static.</br>
Refactored OBS methods to static.</br>
</br>
v2.038 01/01/2024</br>
Add some logging to showSource and setSourceFilterVisiblity in main and writeData in OBSInterface.</br>
</br>
v2.037 01/01/2024</br>
Fix Reset All and Clear All buttons not clearing the new Team Game Show sources in OBS.</br>
</br>
v2.036 12/29/2023</br>
Check for null when reading from auto score in Main createAutoScoreWorker().</br>
Resize AutoScoreConfigFrame and improve AutoScoreConfigPanel layout.</br>
</br>
v2.035 12/29/2023</br>
Add Team X Game X Show sources and make source visible when team X Game X is won. This allows showing an image for each game won instead of just displaying a number.</br>
More Logger refactoring.</br>
</br>
v2.034 12/28/2023</br>
Added Labels to Statistics Display Panel.</br>
Default Team Names to Team 1,2,3.</br>
Reset Teams resets Team Names back to default.</br>
</br>
v2.033 12/28/2023</br>
Refactor Frame classes and remove unnecessary LookAndFeel logic.</br>
Add backup LookAndFeel in Main if Nimbus not loaded.</br>
Add AppConfig class.</br>
</br>
v2.032 12/27/2023</br>
Check for null state to prevent null pointer exception in AutoScoreWorkerStateChangeListener in Main.</br>
Refactor OBSInterface, StreamIndexer, CodeCommand, Memento.</br>
Refactor Command classes to use final.</br>
</br>
v2.031 12/26/2023</br>
Enhance Team Forward/Goalie names logic to only show slash if both names present in statistics display panel and in game table window.</br>
</br>
v2.030 12/26/2023</br>
Removed unneeded variable declaration in getString(String, String) method in Messages.</br>
Improve Game Table Window sizing.</br>
Refactor setFocusOnCode() in Main and other minor refactors in Main.</br>
Double clicking anywhere in the Statistics Display Panel will now copy all the stats to the clipboard.</br>
</br>
v2.029 12/26/2023</br>
Correct typo in README.md.</br>
Add king seat indicators to Game Windows.</br>
Pull king seat indicator from message.properties.</br>
</br>
v2.028 12/24/2023</br>
Reduce redundant code.</br>
Update teamPanel3 Mnemonics when hotkeys saved.</br>
Add more calls to statsEntryPanel.setFocusOnCode for better code entry/hot key usage.</br>
Add ability to copy with double click a single item or control A/control C for entire list from the History stats box. Still doesn't show as selected though.</br>
Remove weight for labels in Tournament Information Panel to let text boxes get more room.</br>
Bold column headers in statsSourcesPanel and filtersPanel.</br>
Update images for README file.</br>
Update documentation in README file.</br>
</br>
v2.027 12/24/2023</br>
Reduce code in Match.</br>
</br>
v2.026 12/24/2023</br>
Refactor WindowListeners to WindowAdapter to reduce code in MainController and Main.</br>
Delete Game class.</br>
Add End Match Hot Key logic.</br>
Move match.getMatchWon() check so it applies to cutthroat and regular modes.</br>
Add gameResults source and supporting logic.</br>
</br>
v2.025 12/23/2023</br>
Rearranged some methods in TeamController and MatchController.</br>
Remove Statistics settings screen.</br>
</br>
v2.024 12/17/2023</br>
Refactor Team to reduce code duplication in set methods.</br>
Fix bug with MatchCount logic not updating when directly changed in text box.</br>
Add property change listeners to Team for score, gamecount, matchcount, timeoutcount.</br>
Refactor TeamController to use TeamsMap and TeamPanelsMap instead of teams[], teampanels[].</br>
</br>
v2.023 12/15/2023</br>
Work on match count logic.</br>
Added missing Team 3 to stream log for cutthroat mode.</br>
</br>
v2.022 12/14/2023</br>
Add Match Count to Team Panel.</br>
Add Match Count to Hot Keys and Sources.</br>
Remove obsolete forward/goalie scores methods.</br>
</br>
v2.021 12/14/2023</br>
Rework HotKeys Panel for Team 3.</br>
</br>
v2.020 12/12/2023</br>
Update copyright to 2024.</br>
Add King Seat logic.</br>
Removed Show Scores from OBS Menu Bar.</br>
Fix Cutthroat Streaming Log.</br>
</br>
v2.019 12/08/2023</br>
Play with panel sizes.</br>
Limit stats to team 1 and 2 only.</br>
</br>
v2.018 12/08/2023</br>
Implement Show Cutthroat checkbox logic.</br>
Change Show Score, Enable Skunk, Show Timer to check boxes and rearrange order.</br>
</br>
v2.017 12/06/2023</br>
More refactoring of source/statssource variables/methods.</br>
Fix stats source and source reset default bugs.</br>
Start on adding Show Cutthroat in OBS Panel.</br>
</br>
v2.016 12/05/2023</br>
Add stats source window.</br>
Add Show Cutthroat source.</br>
Resize Sources window.</br>
</br>
v2.015 12/05/2023</br>
Fix team 2 hotkeys being assigned to team 3.</br>
Refactor team hotkeys methods to paramaterize team number.</br>
</br>
v2.014 12/04/2023</br>
Separate stat sources from other sources.</br>
Redesign Sources Panel.</br>
</br>
v2.013 12/03/2023</br>
Add team3 info to sources in settings.</br>
Fix tournament source that was incorrectly defaulting to tablename.</br>
</br>
v2.012 12/03/2023</br>
Remove display hack for cutthroat.</br>
Refactor cutthroat rotate logic.</br>
Refactor team commands to pass team number.</br>
</br>
v2.011 11/27/2023</br>
Add 3rd Team object and show in MainFrame.</br>
Add supporting Commands for 3rd Team.</br>
Simplify config Property setters in Settings.</br>
Add getMaxGameNumber method to Settings.</br>
Refactored a lot of "Team 1" if statements to allow for a "Team 3" and reduce code.</br>
Update a lot of copyright dates.</br>
Probably some other stuff I forgot I did. Did way too much in this release.</br>
</br>
v2.010 10/29/2023</br>
Initialize Tournament, Event and set cursor in Code input on start up.</br>
</br>
v2.009 09/19/2023</br>
Add test buttons to Filter settings screen.</br>
</br>
v2.008 08/13/2023</br>
Start new match if match won is set and file watch worker sees a new name.</br>
</br>
v2.007 06/30/2023</br>
Clear button in Tournament panel now clears related fields in OBS.</br>
</br>
v2.006 04/05/2023</br>
Add more INFO logging for AutoScore disconnects.</br>
Add auto reconnect AutoScore logic.</br>
</br>
v2.005 03/12/2023</br>
Allow timeouts to exceed limits.</br>
</br>
v2.004 03/04/2023</br>
Fix TimeOut filter not being called from Auto FoosScore</br>
Rework checkFilters so it is called from processCode</br>
</br>
v2.003 02/04/2023</br>
Allow Score, Game/Match, Skunk filters to be shown simultaneously.</br>
Auto switch team 2 players after game won in cutthroat.</br>
</br>
v2.002 02/04/2023</br>
Add logic to handle Time Out commands from Auto Score.</br>
Update the Auto Score configuration validation for new settings.</br>
</br>
v2.001 01/22/2023</br>
Don't send empty/null source to OBS.</br>
Correct GameClock log class.</br>
Correct GameTime,MatchTime,StreamTime sources in Settings.</br>
</br>
v2.000 01/01/2023</br>
Caution: Must use AutoScore v2.0 or above starting with this version of FoosOBSPlus.</br>
Send config to AutoScore when Send Config button pressed.</br>
Rework AutoScore interface and AutoScore Config screen.</br>
Send/Receive AutoScore config.</br>
Validate AutoScore config.</br>
Set connect false when auto score connection drops from auto score end.</br>
</br>
v1.121 12/28/2022</br>
Remove Team Clear buttons.</br>
Fix bug in MaxPossibleGamesToWin when Games To Win changes.</br>
Refactor SettingsSaveListener to ParametersSaveListener.</br>
</br>
v1.120 12/27/2022</br>
Update README.md.</br>
Make Reset Scores button update game table properly.</br>
</br>
v1.119 12/24/2022</br>
Put log4j2.properties in src folder and set path to ./ so logs are created in same directory as program runs.</br>
Fix HotKeysBaseScript default script in messages.properties.</br>
</br>
v1.118 12/23/2022</br>
Add logging using log4j2 and rollingfile appender.</br>
Update copyright on all files.</br>
</br>
v1.117 12/18/2022</br>
Timers now use System.currentTimeMillis() for better accuracy.</br>
Add Stream Time source to Sources.</br>
</br>
v1.116 12/17/2022</br>
Use System user.dir property to get current path of program and use that in the autohotkey scripts.</br>
Force autohotkey script path to end in \.</br>
Check directories exist before created autohotkey scripts.</br>
Remove Select Path from Filters Panel.</br>
</br>
v1.115 12/13/2022</br>
Drop Load and Set buttons in Tournament Panel.</br>
Rename TableController and Table classes to TournamentController and Tournament respectively.</br>
Remove table name and file path logic from OBSInterface.</br>
</br>
v1.114 12/13/2022</br>
Make Start Stream button toggle with Stop Stream and show Stream Timer.</br>
Update documentation.</br>
</br>
v1.113 12/10/2022</br>
Make base hotkey script a parameter instead of file.</br>
Change TablePanel to TournamentPanel.</br>
Add HotKey Script Path to HotKeys settings.</br>
</br>
v1.112 12/09/2022</br>
Fix Rack Mode scoring logic bug when winning points were less than required amount for non Rack Mode.</br>
Update ReadME.md file with more documentation.</br>
</br>
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
