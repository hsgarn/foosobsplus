# FoosOBSPlus
FoosOBSPlus is a flexible foosball score keeper and statistics program compatible with OBS Studio and also plays well with Elagto's Stream Deck products.

## Overview
FoosOBSPlus was written to update text files used by OBS Studio to display scores and game counts while live streaming foosball matches.  Almost every field displayed in FoosOBSPlus can be output to a file that OBS Studio can then read and display in a scene. 

FoosOBSPlus Main Screen:
<img align="left" width="850" height="630" src="https://github.com/hsgarn/foosOBSPlus/blob/master/foosOBSPlusScreen1.png">

OBS Studio scene utilizing FoosOBSPlus text files:
<img align="left" width="1920" height="1090" src="https://github.com/hsgarn/foosOBSPlus/blob/master/foosOBSPlusScreen2.png">

## Setup
FoosOBSPlus is a java program. You can download the source and export to a jar file. I do this in eclipse so am not sure how to do it at command line or in other ide's, though I'm sure google is your friend and will provide you with that information.  Windows will need to have at least Java 1.8 loaded and set to associate jar files with java. 

## Settings
If running FoosOBSPlus for the first time, it will create a config.properties file with the default properties in the folder in which the program is running. These properties contain the settings for the operating parameters, filenames and hot keys.

### Operating Parameters
FoosOBSPlus tries to make keeping track of a foosball match's progress as simple as possible.  To that end, there is a settings screen that contains parameters that affect how FoosOBSPlus will behave.  Click Edit then Settings then Parameters to get to the Parameter Settings screen.

<img width="640" height="480" src="https://github.com/hsgarn/foosOBSPlus/blob/master/foosOBSPlusSettings1.png">

Parameter Settings Page:
<img width="552" height="442" src="https://github.com/hsgarn/foosOBSPlus/blob/master/foosOBSPlusSettings2.png">

#### Points to Win
This is the number of points required to win a game.  This is only used if the Auto Increment Game checkbox is checked. Once a team's score reaches this number, it will reset the scores to zero and increase the game counter for the team.  If the Announce Winner check box is set and the team has reached the number set in the Games to Win parameter, then the Team's name will be written to the Match Winner file along with the text specified in the Winner Prefix and Winner Suffix parameters.  Points to Win is also utilized to determine if it is meatball (both teams 1 point away from winning score in final game).

#### Max Win
The Max Win parameter is only used when the Win By parameter is greater than 1.  If a team has to win by more than 1 point, then Max Win is the maximum score a team can get and at that point it does not matter if they won by more than 1 point.

#### Win By
The Win By parameter is used to force a team to win by a specified number of points.  Default is 1 which means that a team only has to win by 1 point (normal game behavior).  Setting the parameter to 2 would require that a team win by two points.  If a team reaches the Points to Win value without being ahead by the Win By value, then the game continues until either a team wins by the Win By margin, or the Max Win value is reached.

#### Games to Win
The Games to Win parameter is used to determine when a match is won.  Specify the number of games required to win the match.  Default is 2 for a 2 out of 3 match.  Use 3 for a 3 out of 5 match. etc.

#### Max Time Outs
The Max Time Outs parameter is used to set how many time outs each team has.  The default is 2.

#### Team 1 Last Scored
This parameter defines the text that will be put in the Last Scored text file when Team 1 scores.  The default value is "<--- Last Scored".

#### Team 2 Last Scored
This parameter defines the text that will be put in the Last Scored text file when Team 2 scores.  The default value is "Last Scored --->".

#### Clear Last Scored
This parameter defines the text that will be put in the Last Scored text file when neither team has scored (after the Reset All button is clicked or a score is reduced by the - button).

#### Team 1 Color
This is the color of the player figures for Team 1. Default is Yellow. This is used to help recognize which side of the table Team 1 is on.

#### Team 2 Color
This is the color of the player figures for Team 2. Default is Black. This is used to help recognize which side of the table Team 2 is on.

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

#### Auto Increment Game checkbox
When checked, the game count for the team that just scored will be automatically incremented when their score is incremented and reaches the number of points required to win a game.

#### Announce Winner checkbox
When checked, the Match Winner file will be populated with the winning team name (prefixed by the Winner Prefix parameter and suffixed by the Winner Suffix parameter) when the game count for a team reaches the Games to Win parameter value.  Use the Reset Scores or Reset All buttons to clear the Match Winner file for the next match.

#### Winner Prefix
Text to be displayed in front of the winning teams name when the match is won.  The default is Match Winner:.

#### Winner Suffix
Text to be displayed after the winning teams name when the match is won.  The default is !!!.

#### Announce Meatball checkbox and text field
When checked, the Meatball file will be populated with the text in the Announce Meatball parameter when both teams' scores are one point away from winning.  Once the score changes, the text in the Meatball file will be automatically cleared.

#### Show Time Outs Used
When checked, the program will put the number of time outs used for each team in the Time Out files.  When unchecked, the program will put the number of time outs remaining for each team in the Time Out Files.

#### Auto Capitalize Names
When checked, the program will automatically capitalize the first letter of each team's player's names.  If not checked, the team names are left as entered.

### FileNames
FoosOBSPlus writes out most of it's data to text files so that it can be read by programs such as OBS Studio and displayed in a live video stream.  The names of these files are configurable if the default names do not suit you.  FoosOBSPlus also allows you to run more than one table at a time.  It does this by prefixing each file with the table name.  To get to the filenames configuration, click on Edit, then Settings, then File Names:

<img align="left" width="552" height="442" src="https://github.com/hsgarn/foosOBSPlus/blob/master/foosOBSPlusSettings3.png">

<img align="left" width="502" height="302" src="https://github.com/hsgarn/foosOBSPlus/blob/master/foosOBSPlusSettings5.png">

Below are the file names that can be configured:

#### Team 1
This is the filename for Team 1's name(s). Default filename is team1name.txt.

#### Team 2
This is the filename for Team 2's name(s). Default filename is team2name.txt.

#### Game Count 1
This is the filename for Team 1's game count. Default filename is gamecount1.txt.

#### Game Count 2
This is the filename for Team 2's game count. Default filename is gamecount2.txt.

#### Score 1
This is the filename for Team 1's score. Default filename is score1.txt.

#### Score 2
This is the filename for Team 2's score. Default filename is score2.txt.

#### Time Out 1
This is the filename for Team 1's time outs used or remaining depending the Show Time Outs Used checkbox. Default filename is timeout1.txt.

#### Time Out 2
This is the filename for Team 2's time outs used or remaining depending the Show Time Outs Used checkbox. Default filename is timeout2.txt.

#### Reset 1
This is the filename for Team 1's Reset flag.  Default filename is reset1.txt.

#### Reset 2
This is the filename for Team 2's Reset flag.  Default filename is reset2.txt.

#### Warn 1
This is the filename for Team 1's Reset Warning flag.  Default filename is warn1.txt.

#### Warn 2
This is the filename for Team 2's Reset Warning flag.  Default filename is warn2.txt.

#### Game Time
This is the filename for the game clock time.  Default filename is gametime.txt.

#### Match Time
This is the filename for the match time.  Default filename is matchtime.txt.

#### Team 1 Forward
This is the filename that holds Team 1's forward's name.  Default filename is team1forward.txt

#### Team 1 Goalie
This is the filename that holds Team 1's goalie's name.  Default filename is team1goalie.txt

#### Team 2 Forrward
This is the filename that holds Team 2's forward's name.  Default filename is team2forward.txt

#### Team 2 Goalie
This is the filename that holds Team 2's goalie's name.  Default filename is team2goalie.txt

#### Tournament
This is the filename of a freeform text field that can be used for the name of the tournament or venue.  Default filename is tournament.txt.

#### Event
This is the filename of a freeform text field that can be used for the name of the event being played (i.e. DYP, Open Singles, etc).  Default filename is event.txt.

#### Time Remaining
This is the filename for the Time Remaining on the current timer.  Default filename is timeremaining.txt.

#### Timer
This is the filename that holds the name of the current timer that is running (Shot, Pass, Game, Timeout, Recall). Default filename is timerinuse.txt.

#### Match Winner
This is the filename that holds the Winner Prefix, Team's Name and Winner Suffix of the team that won the match.  Default filename is matchwinner.txt.

#### Meatball
This is the filename that holds the Meatball text when a game is tied just prior to the final point.  Default filename is meatball.txt.

#### Last Scored
This is the filename that holds the indicator for which team scored last.  Default filename is lastscored.txt.

#### Stuffs 1
This is the filename that holds the number of stuffs team 1 has done.  Default filename is stuffs1.txt.

#### Stuffs 2
This is the filename that holds the number of stuffs team 2 has done.  Default filename is stuffs2.txt.

#### Breaks 1
This is the filename that holds the number of breaks team 1 has gotten.  Default filename is breaks1.txt.

#### Breaks 2
This is the filename that holds the number of breaks team 2 has gotten.  Default filename is breaks2.txt.

#### Team 1 Pass Attempts
This is the filename that holds the number of pass attempts for team 1.  Default filename is team1passattempts.txt.

#### Team 1 Pass Completes
This is the filename that holds the number of pass completions for team 1.  Default filename is team1passcompletes.txt.

#### Team 2 Pass Attempts
This is the filename that holds the number of pass attempts for team 2.  Default filename is team2passattempts.txt.

#### Team 2 Pass Completes
This is the filename that holds the number of pass completions for team 2.  Default filename is team2passcompletes.txt.

#### Team 1 Shot Attempts
This is the filename that holds the number of shot attempts for team 1.  Default filename is team1shotattempts.txt.

#### Team 1 Shot Completes
This is the filename that holds the number of shots made for team 1.  Default filename is team1shotcompletes.txt.

#### Team 2 Shot Attempts
This is the filename that holds the number of shot attempts for team 2.  Default filename is team2shotattempts.txt.

#### Team 2 Shot Completes
This is the filename that holds the number of shots made for team 2.  Default filename is team2shotcompletes.txt.

#### Team 1 Clear Attempts
This is the filename that holds the number of clearing attempts for team 1.  Default filename is team1clearattempts.txt.

#### Team 1 Clear Completes
This is the filename that holds the number of successful clears for team 1 from the goalie area to the 5 bar or beyond.  Default filename is team1clearcompletes.txt.

#### Team 2 Clear Attempts
This is the filename that holds the number of successful clears for team 2 from the goalie area to the 5 bar or beyond.  Default filename is team2clearcompletes.txt.

#### Team 2 Clear Completes
This is the filename that holds the number of successful clears for team 2 from the goalie area to the 5 bar or beyond.  Default filename is team2clearcompletes.txt.

#### Team 1 2-Bar Pass Attempts
This is the filename that holds the number of pass attempts for team 1 from the 2-bar to the 5-bar or 3-bar.  Default filename is team1twobarpassattempts.txt.

#### Team 1 2-Bar Pass Completes
This is the filename that holds the number of succesful pass completions for team 1 from the 2-bar to the 5 or 3-bar.  Default filename is team1twobarpasscompletes.txt

#### Team 2 2-Bar Pass Attempts
This is the filename that holds the number of pass attempts for team 2 from the 2-bar to the 5-bar or 3-bar.  Default filename is team2twobarpassattempts.txt.

#### Team 2 2-Bar Pass Completes
This is the filename that holds the number of succesful pass completions for team 2 from the 2-bar to the 5 or 3-bar.  Default filename is team2twobarpasscompletes.txt

#### Team 1 Pass Percent
This is the filename that holds the successful passing percentage for team 1.  Default filename is team1passpercent.txt.

#### Team 2 Pass Percent
This is the filename that holds the successful passing percentage for team 2.  Default filename is team2passpercent.txt.

#### Team 1 Shot Percent
This is the filename that holds the successful shot percentage for team 1.  Default filename is team1shotpercent.txt.

#### Team 2 Shot Percent
This is the filename that holds the successful shot percentage for team 2.  Default filename is team2shotpercent.txt.

#### Team 1 Clear Percent
This is the filename that holds the successful clear percentatge for team 1.  Default filename is team1clearpercent.txt.

#### Team 2 Clear Percent
This is the filename that holds the successful clear percentatge for team 2.  Default filename is team2clearpercent.txt.

#### Team 1 Scoring
This is the filename that holds the number of scores for team 1.  Default filename is team1scoring.txt.

#### Team 2 Scoring
This is the filename that holds the number of scores for team 2.  Default filename is team2scoring.txt.

#### Team 1 3-Bar Scoring
This is the filename that holds the number of scores from the 3-bar for team 1.  Default filename is team1threebarscoring.txt.

#### Team 2 3-Bar Scoring
This is the filename that holds the number of scores from the 3-bar for team 2.  Default filename is team2threebarscoring.txt.

#### Team 1 5-Bar Scoring
This is the filename that holds the number of scores from the 5-bar for team 1.  Default filename is team1fivebarscoring.txt.

#### Team 2 5-Bar Scoring
This is the filename that holds the number of scores from the 5-bar for team 2.  Default filename is team2fivebarscoring.txt.

#### Team 1 2-Bar Scoring
This is the filename that holds the number of scores from the 2-bar for team 1.  Default filename is team1twobarscoring.txt.

#### Team 2 2-Bar Scoring
This is the filename that holds the number of scores from the 2-bar for team 2.  Default filename is team2twobarscoring.txt.

#### Team 1 Shots On Goal
This is the filename that holds the number of shots on goal for team 1.  Default filename is team1shotsongoal.txt.

#### Team 2 Shots On Goal
This is the filename that holds the number of shots on goal for team 2.  Default filename is team2shotsongoal.txt.

#### Save
Click the save button to save any filename changes made.

#### Cancel
Click the cancel button to discard any filename changes made.

#### Restore Defaults
Click the Restore Defaults button to restore the default filenames.

### Hot Keys
FoosOBSPlus uses buttons to do various functions such as increase or decrease scores, switch sides, reset game counts, start timers, etc.  Each button can have a Hot Key assigned to it.  Pressing ALT plus the assigned Hot Key for the button will function just like pressing the actual button.  The hot keys can be used in Stream Deck commands to make operating FoosOBSPlus a simple push button affair.

<img align="left" width="552" height="442" src="https://github.com/hsgarn/foosOBSPlus/blob/master/foosOBSPlusSettings4.png">

<img align="left" width="802" height="442" src="https://github.com/hsgarn/foosOBSPlus/blob/master/foosOBSPlusSettings6.png">

#### Start Match
Assigns the hot key for the Start Match button.  Default hot key is unassigned.

#### Pause Match
Assigns the hot key for the Pause Match button.  Default hot key is unassigned.

#### Start Game
Assigns the hot key for the Start Game button.  Default hot key is unassigned.

#### Reset(Team 1)
Assigns the hot key for the Reset button for Team 1.  Default hot key is z.

#### Reset(Team 2)
Assigns the hot key for the Reset button for Team 2.  Default hot key is ,.

#### Warn(Team 1)
Assigns the hot key for the Warn button for Team 1. Default hot key is x.

#### Warn(Team 2)
Assigns the hot key for the Warn button for Team 2. Default hot key is ..

#### <-Switch->(Reset/Warn)
Assigns the hot key to swap the Reset and Warn flags for Team 1 with Team 2.  Deafult hot key is not assigned.

#### Team Names
##### Clear(Team 1)
Assigns the hot key for the Clear button for Team 1's name. Default hot key is unassigned.

##### Team 1 Switch Positions
Assigns the hot key to Switch Team 1 and Team 2's names. Default hot key is t.

##### Clear(Team 2)
Assigns the hot key for the Clear button for Team 2's name. Default hot key is unassigned.

##### Team 2 Switch Positions
Assigns the hot key for the Switch button for Team 2's names. Default hot key is m.

##### Switch Teams
Assigns the hot key to swap Team 1's and Team 2's names (Team, Forward & Goalid) with each other.  Default hot key is e.

#### Scores
##### -(Team 1)
Assigns the hot key for the - button for Team 1's score. Default hot key is unassigned.

##### +(Team 1)
Assigns the hot key for the + button to increase Team 1's score. Default hot key is 1.

##### -(Team 2)
Assigns the hot key for the - button for Team 2's score. Default hot key is unassigned.

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
Assigns the hot key for the - button for Team 1's score. Default hot key is unassigned.

##### +(Team 1)
Assigns the hot key for the + button to increase Team 1's game count. Default hot key is 5.

##### -(Team 2)
Assigns the hot key for the - button for Team 2's score. Default hot key is unassigned.

##### +(Team 2)
Assigns the hot key for the + button to increase Team 2's game count. Default hot key is 6.

##### Switch Game Counts
Assigns the hot key to switch the game counts. Team 1's game count will be swapped with Team 2's game count.  Default hot key is unassigned.

#### Time Outs
##### Return TO(Team 1)
Assigns the hot key to return a time out to Team 1's available pool of timeouts or used pool of time outs depending on how the Show Time Outs Used setting is set.  Default hot key is unassigned.

##### Use TO(Team 1)
Assigns the hot key to use a time out for Team 1.  Default hot key is 9.

##### Return TO(Team 2)
Assigns the hot key to return a time out to Team 2's available pool of timeouts or used pool of time outs depending on how the Show Time Outs Used setting is set.  Default hot key is unassigned.

##### Use TO(Team 2)
Assigns the hot key to use a time out for Team 2.  Default hot key is 0.

##### Switch Time Outs
Assigns the hot key to switch the time out counts. Team 1's time out count will be swapped with Team 2's time out count.  Default hot key is unassigned.

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

#### Save
Click the save button to save any hot key changes made.

#### Cancel
Click the cancel button to discard any hot keye changes made.

#### Restore Defaults
Click the Restore Defaults button to restore the default hot keys.



