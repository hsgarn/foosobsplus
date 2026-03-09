/**
Copyright © 2020-2026 Hugh Garner
Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in
all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL
THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR
OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE,
ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR
OTHER DEALINGS IN THE SOFTWARE.
**/
package com.midsouthfoosball.foosobsplus.model;

/**
 * Constants for all Settings property keys.
 * Use these instead of inline string literals to get compile-time safety
 * and IDE refactoring support.
 */
public final class SettingsKeys {
    private SettingsKeys() {}

    // ==================== Control / Parameter Keys ====================
    public static final String CTRL_LOGO_LINK           = "LogoLink";
    public static final String CTRL_ICON_IMG_PATH       = "IconImgPath";
    public static final String CTRL_LOGO_IMG_PATH       = "LogoImgPath";
    public static final String CTRL_SHOW_PARSED         = "ShowParsed";
    public static final String CTRL_TABLE_NAME          = "TableName";
    public static final String CTRL_DATAPATH            = "datapath";
    public static final String CTRL_POINTS_TO_WIN       = "PointsToWin";
    public static final String CTRL_MAX_WIN             = "MaxWin";
    public static final String CTRL_WIN_BY              = "WinBy";
    public static final String CTRL_GAMES_TO_WIN        = "GamesToWin";
    public static final String CTRL_MAX_TIME_OUTS       = "MaxTimeOuts";
    public static final String CTRL_AUTO_INCREMENT_GAME = "AutoIncrementGame";
    public static final String CTRL_ANNOUNCE_WINNER     = "AnnounceWinner";
    public static final String CTRL_ANNOUNCE_MEATBALL   = "AnnounceMeatball";
    public static final String CTRL_WINNER_PREFIX       = "WinnerPrefix";
    public static final String CTRL_WINNER_SUFFIX       = "WinnerSuffix";
    public static final String CTRL_MEATBALL            = "Meatball";
    public static final String CTRL_TEAM1_LAST_SCORED   = "Team1LastScored";
    public static final String CTRL_TEAM2_LAST_SCORED   = "Team2LastScored";
    public static final String CTRL_TEAM3_LAST_SCORED   = "Team3LastScored";
    public static final String CTRL_CLEAR_LAST_SCORED   = "ClearLastScored";
    public static final String CTRL_SIDE1_COLOR         = "Side1Color";
    public static final String CTRL_SIDE2_COLOR         = "Side2Color";
    public static final String CTRL_SIDE3_COLOR         = "Side3Color";
    public static final String CTRL_BALLS_IN_RACK       = "BallsInRack";
    public static final String CTRL_NAME_SEPARATOR      = "NameSeparator";
    public static final String CTRL_SHOT_TIME           = "ShotTime";
    public static final String CTRL_PASS_TIME           = "PassTime";
    public static final String CTRL_TIME_OUT_TIME       = "TimeOutTime";
    public static final String CTRL_GAME_TIME           = "GameTime";
    public static final String CTRL_RECALL_TIME         = "RecallTime";
    public static final String CTRL_SHOW_TIME_OUTS_USED = "ShowTimeOutsUsed";
    public static final String CTRL_AUTO_CAP_NAMES      = "AutoCapNames";
    public static final String CTRL_WIN_BY_FINAL_ONLY   = "WinByFinalOnly";
    public static final String CTRL_SHOW_SKUNK          = "ShowSkunk";
    public static final String CTRL_CUT_THROAT_MODE     = "CutThroatMode";
    public static final String CTRL_RACK_MODE           = "RackMode";
    public static final String CTRL_LOGO_IMAGE_URL      = "LogoImageURL";
    public static final String CTRL_LOGO_LINK_URI       = "LogoLinkURI";

    // ==================== OBS Keys ====================
    public static final String OBS_HOST                 = "OBSHost";
    public static final String OBS_PORT                 = "OBSPort";
    public static final String OBS_PASSWORD             = "OBSPassword";
    public static final String OBS_MAIN_SCENE           = "OBSMainScene";
    public static final String OBS_AUTO_LOGIN           = "OBSAutoLogin";
    public static final String OBS_SAVE_PASSWORD        = "OBSSavePassword";
    public static final String OBS_CLOSE_ON_CONNECT     = "OBSCloseOnConnect";
    public static final String OBS_UPDATE_ON_CONNECT    = "OBSUpdateOnConnect";

    // ==================== Source Keys ====================
    public static final String SRC_TOURNAMENT           = "Tournament";
    public static final String SRC_EVENT                = "Event";
    public static final String SRC_TABLE_NAME           = "TableName";
    public static final String SRC_TEAM1_NAME           = "Team1Name";
    public static final String SRC_TEAM1_FORWARD        = "Team1Forward";
    public static final String SRC_TEAM1_GOALIE         = "Team1Goalie";
    public static final String SRC_TEAM2_NAME           = "Team2Name";
    public static final String SRC_TEAM2_FORWARD        = "Team2Forward";
    public static final String SRC_TEAM2_GOALIE         = "Team2Goalie";
    public static final String SRC_TEAM3_NAME           = "Team3Name";
    public static final String SRC_TEAM3_FORWARD        = "Team3Forward";
    public static final String SRC_TEAM3_GOALIE         = "Team3Goalie";
    public static final String SRC_TEAM1_SCORE          = "Team1Score";
    public static final String SRC_TEAM2_SCORE          = "Team2Score";
    public static final String SRC_TEAM3_SCORE          = "Team3Score";
    public static final String SRC_TEAM1_GAME_COUNT     = "Team1GameCount";
    public static final String SRC_TEAM2_GAME_COUNT     = "Team2GameCount";
    public static final String SRC_TEAM3_GAME_COUNT     = "Team3GameCount";
    public static final String SRC_TEAM1_MATCH_COUNT    = "Team1MatchCount";
    public static final String SRC_TEAM2_MATCH_COUNT    = "Team2MatchCount";
    public static final String SRC_TEAM3_MATCH_COUNT    = "Team3MatchCount";
    public static final String SRC_TEAM1_TIME_OUT       = "Team1TimeOut";
    public static final String SRC_TEAM2_TIME_OUT       = "Team2TimeOut";
    public static final String SRC_TEAM3_TIME_OUT       = "Team3TimeOut";
    public static final String SRC_TEAM1_RESET          = "Team1Reset";
    public static final String SRC_TEAM2_RESET          = "Team2Reset";
    public static final String SRC_TEAM3_RESET          = "Team3Reset";
    public static final String SRC_TEAM1_WARN           = "Team1Warn";
    public static final String SRC_TEAM2_WARN           = "Team2Warn";
    public static final String SRC_TEAM3_WARN           = "Team3Warn";
    public static final String SRC_TEAM1_KING_SEAT      = "Team1KingSeat";
    public static final String SRC_TEAM2_KING_SEAT      = "Team2KingSeat";
    public static final String SRC_TEAM3_KING_SEAT      = "Team3KingSeat";
    public static final String SRC_TEAM1_GAME1_SHOW     = "Team1Game1Show";
    public static final String SRC_TEAM2_GAME1_SHOW     = "Team2Game1Show";
    public static final String SRC_TEAM3_GAME1_SHOW     = "Team3Game1Show";
    public static final String SRC_TEAM1_GAME2_SHOW     = "Team1Game2Show";
    public static final String SRC_TEAM2_GAME2_SHOW     = "Team2Game2Show";
    public static final String SRC_TEAM3_GAME2_SHOW     = "Team3Game2Show";
    public static final String SRC_TEAM1_GAME3_SHOW     = "Team1Game3Show";
    public static final String SRC_TEAM2_GAME3_SHOW     = "Team2Game3Show";
    public static final String SRC_TEAM3_GAME3_SHOW     = "Team3Game3Show";
    public static final String SRC_MATCH_WINNER         = "MatchWinner";
    public static final String SRC_MEATBALL             = "Meatball";
    public static final String SRC_GAME_RESULTS         = "GameResults";
    public static final String SRC_LAST_SCORED          = "LastScored";
    public static final String SRC_GAME_TIME            = "GameTime";
    public static final String SRC_MATCH_TIME           = "MatchTime";
    public static final String SRC_STREAM_TIME          = "StreamTime";
    public static final String SRC_TIME_REMAINING       = "TimeRemaining";
    public static final String SRC_TIMER_IN_USE         = "TimerInUse";
    public static final String SRC_SHOW_SCORES          = "ShowScores";
    public static final String SRC_SHOW_TIMER           = "ShowTimer";
    public static final String SRC_SHOW_CUTTHROAT       = "ShowCutthroat";
    public static final String SRC_SIDE1_COLOR          = "Side1Color";
    public static final String SRC_SIDE2_COLOR          = "Side2Color";
    public static final String SRC_SIDE3_COLOR          = "Side3Color";
    public static final String SRC_CUE_BALL             = "CueBall";
    public static final String SRC_ONE_BALL             = "OneBall";
    public static final String SRC_TWO_BALL             = "TwoBall";
    public static final String SRC_THREE_BALL           = "ThreeBall";
    public static final String SRC_FOUR_BALL            = "FourBall";
    public static final String SRC_FIVE_BALL            = "FiveBall";
    public static final String SRC_SIX_BALL             = "SixBall";
    public static final String SRC_SEVEN_BALL           = "SevenBall";
    public static final String SRC_EIGHT_BALL           = "EightBall";
    public static final String SRC_NINE_BALL            = "NineBall";
    public static final String SRC_TEN_BALL             = "TenBall";
    public static final String SRC_ELEVEN_BALL          = "ElevenBall";
    public static final String SRC_TWELVE_BALL          = "TwelveBall";
    public static final String SRC_THIRTEEN_BALL        = "ThirteenBall";
    public static final String SRC_FOURTEEN_BALL        = "FourteenBall";
    public static final String SRC_FIFTEEN_BALL         = "FifteenBall";

    // ==================== StatsSource Keys ====================
    public static final String STAT_TEAM1_PASS_ATTEMPTS        = "Team1PassAttempts";
    public static final String STAT_TEAM2_PASS_ATTEMPTS        = "Team2PassAttempts";
    public static final String STAT_TEAM3_PASS_ATTEMPTS        = "Team3PassAttempts";
    public static final String STAT_TEAM1_PASS_COMPLETES       = "Team1PassCompletes";
    public static final String STAT_TEAM2_PASS_COMPLETES       = "Team2PassCompletes";
    public static final String STAT_TEAM3_PASS_COMPLETES       = "Team3PassCompletes";
    public static final String STAT_TEAM1_PASS_PERCENT         = "Team1PassPercent";
    public static final String STAT_TEAM2_PASS_PERCENT         = "Team2PassPercent";
    public static final String STAT_TEAM3_PASS_PERCENT         = "Team3PassPercent";
    public static final String STAT_TEAM1_SHOT_ATTEMPTS        = "Team1ShotAttempts";
    public static final String STAT_TEAM2_SHOT_ATTEMPTS        = "Team2ShotAttempts";
    public static final String STAT_TEAM3_SHOT_ATTEMPTS        = "Team3ShotAttempts";
    public static final String STAT_TEAM1_SHOT_COMPLETES       = "Team1ShotCompletes";
    public static final String STAT_TEAM2_SHOT_COMPLETES       = "Team2ShotCompletes";
    public static final String STAT_TEAM3_SHOT_COMPLETES       = "Team3ShotCompletes";
    public static final String STAT_TEAM1_SHOT_PERCENT         = "Team1ShotPercent";
    public static final String STAT_TEAM2_SHOT_PERCENT         = "Team2ShotPercent";
    public static final String STAT_TEAM3_SHOT_PERCENT         = "Team3ShotPercent";
    public static final String STAT_TEAM1_CLEAR_ATTEMPTS       = "Team1ClearAttempts";
    public static final String STAT_TEAM2_CLEAR_ATTEMPTS       = "Team2ClearAttempts";
    public static final String STAT_TEAM3_CLEAR_ATTEMPTS       = "Team3ClearAttempts";
    public static final String STAT_TEAM1_CLEAR_COMPLETES      = "Team1ClearCompletes";
    public static final String STAT_TEAM2_CLEAR_COMPLETES      = "Team2ClearCompletes";
    public static final String STAT_TEAM3_CLEAR_COMPLETES      = "Team3ClearCompletes";
    public static final String STAT_TEAM1_CLEAR_PERCENT        = "Team1ClearPercent";
    public static final String STAT_TEAM2_CLEAR_PERCENT        = "Team2ClearPercent";
    public static final String STAT_TEAM3_CLEAR_PERCENT        = "Team3ClearPercent";
    public static final String STAT_TEAM1_TWO_BAR_PASS_ATTEMPTS   = "Team1TwoBarPassAttempts";
    public static final String STAT_TEAM2_TWO_BAR_PASS_ATTEMPTS   = "Team2TwoBarPassAttempts";
    public static final String STAT_TEAM3_TWO_BAR_PASS_ATTEMPTS   = "Team3TwoBarPassAttempts";
    public static final String STAT_TEAM1_TWO_BAR_PASS_COMPLETES  = "Team1TwoBarPassCompletes";
    public static final String STAT_TEAM2_TWO_BAR_PASS_COMPLETES  = "Team2TwoBarPassCompletes";
    public static final String STAT_TEAM3_TWO_BAR_PASS_COMPLETES  = "Team3TwoBarPassCompletes";
    public static final String STAT_TEAM1_TWO_BAR_PASS_PERCENT    = "Team1TwoBarPassPercent";
    public static final String STAT_TEAM2_TWO_BAR_PASS_PERCENT    = "Team2TwoBarPassPercent";
    public static final String STAT_TEAM3_TWO_BAR_PASS_PERCENT    = "Team3TwoBarPassPercent";
    public static final String STAT_TEAM1_ACES             = "Team1Aces";
    public static final String STAT_TEAM2_ACES             = "Team2Aces";
    public static final String STAT_TEAM3_ACES             = "Team3Aces";
    public static final String STAT_TEAM1_STUFFS           = "Team1Stuffs";
    public static final String STAT_TEAM2_STUFFS           = "Team2Stuffs";
    public static final String STAT_TEAM3_STUFFS           = "Team3Stuffs";
    public static final String STAT_TEAM1_BREAKS           = "Team1Breaks";
    public static final String STAT_TEAM2_BREAKS           = "Team2Breaks";
    public static final String STAT_TEAM3_BREAKS           = "Team3Breaks";
    public static final String STAT_TEAM1_SCORING          = "Team1Scoring";
    public static final String STAT_TEAM2_SCORING          = "Team2Scoring";
    public static final String STAT_TEAM3_SCORING          = "Team3Scoring";
    public static final String STAT_TEAM1_THREE_BAR_SCORING = "Team1ThreeBarScoring";
    public static final String STAT_TEAM2_THREE_BAR_SCORING = "Team2ThreeBarScoring";
    public static final String STAT_TEAM3_THREE_BAR_SCORING = "Team3ThreeBarScoring";
    public static final String STAT_TEAM1_FIVE_BAR_SCORING  = "Team1FiveBarScoring";
    public static final String STAT_TEAM2_FIVE_BAR_SCORING  = "Team2FiveBarScoring";
    public static final String STAT_TEAM3_FIVE_BAR_SCORING  = "Team3FiveBarScoring";
    public static final String STAT_TEAM1_TWO_BAR_SCORING   = "Team1TwoBarScoring";
    public static final String STAT_TEAM2_TWO_BAR_SCORING   = "Team2TwoBarScoring";
    public static final String STAT_TEAM3_TWO_BAR_SCORING   = "Team3TwoBarScoring";
    public static final String STAT_TEAM1_SHOTS_ON_GOAL     = "Team1ShotsOnGoal";
    public static final String STAT_TEAM2_SHOTS_ON_GOAL     = "Team2ShotsOnGoal";
    public static final String STAT_TEAM3_SHOTS_ON_GOAL     = "Team3ShotsOnGoal";

    // ==================== Filter Keys ====================
    public static final String FILTER_TEAM1_SCORE          = "Team1Score";
    public static final String FILTER_TEAM2_SCORE          = "Team2Score";
    public static final String FILTER_TEAM3_SCORE          = "Team3Score";
    public static final String FILTER_TEAM1_WIN_GAME        = "Team1WinGame";
    public static final String FILTER_TEAM2_WIN_GAME        = "Team2WinGame";
    public static final String FILTER_TEAM3_WIN_GAME        = "Team3WinGame";
    public static final String FILTER_TEAM1_WIN_MATCH       = "Team1WinMatch";
    public static final String FILTER_TEAM2_WIN_MATCH       = "Team2WinMatch";
    public static final String FILTER_TEAM3_WIN_MATCH       = "Team3WinMatch";
    public static final String FILTER_TEAM1_TIME_OUT        = "Team1TimeOut";
    public static final String FILTER_TEAM2_TIME_OUT        = "Team2TimeOut";
    public static final String FILTER_TEAM3_TIME_OUT        = "Team3TimeOut";
    public static final String FILTER_TEAM1_RESET           = "Team1Reset";
    public static final String FILTER_TEAM2_RESET           = "Team2Reset";
    public static final String FILTER_TEAM3_RESET           = "Team3Reset";
    public static final String FILTER_TEAM1_WARN            = "Team1Warn";
    public static final String FILTER_TEAM2_WARN            = "Team2Warn";
    public static final String FILTER_TEAM3_WARN            = "Team3Warn";
    public static final String FILTER_TEAM1_SWITCH_POSITIONS = "Team1SwitchPositions";
    public static final String FILTER_TEAM2_SWITCH_POSITIONS = "Team2SwitchPositions";
    public static final String FILTER_TEAM3_SWITCH_POSITIONS = "Team3SwitchPositions";
    public static final String FILTER_TEAM1_SKUNK           = "Team1Skunk";
    public static final String FILTER_TEAM2_SKUNK           = "Team2Skunk";
    public static final String FILTER_TEAM3_SKUNK           = "Team3Skunk";
    public static final String FILTER_START_MATCH           = "StartMatch";
    public static final String FILTER_START_GAME            = "StartGame";
    public static final String FILTER_SWITCH_SIDES          = "SwitchSides";
    public static final String FILTER_MEATBALL              = "Meatball";

    // ==================== HotKey Keys ====================
    public static final String HOTKEY_TEAM1_SWITCH_POSITIONS  = "Team1SwitchPositions";
    public static final String HOTKEY_TEAM2_SWITCH_POSITIONS  = "Team2SwitchPositions";
    public static final String HOTKEY_TEAM3_SWITCH_POSITIONS  = "Team3SwitchPositions";
    public static final String HOTKEY_TEAM1_SCORE_PLUS        = "Team1ScorePlus";
    public static final String HOTKEY_TEAM2_SCORE_PLUS        = "Team2ScorePlus";
    public static final String HOTKEY_TEAM3_SCORE_PLUS        = "Team3ScorePlus";
    public static final String HOTKEY_TEAM1_SCORE_MINUS       = "Team1ScoreMinus";
    public static final String HOTKEY_TEAM2_SCORE_MINUS       = "Team2ScoreMinus";
    public static final String HOTKEY_TEAM3_SCORE_MINUS       = "Team3ScoreMinus";
    public static final String HOTKEY_TEAM1_GAME_COUNT_PLUS   = "Team1GameCountPlus";
    public static final String HOTKEY_TEAM2_GAME_COUNT_PLUS   = "Team2GameCountPlus";
    public static final String HOTKEY_TEAM3_GAME_COUNT_PLUS   = "Team3GameCountPlus";
    public static final String HOTKEY_TEAM1_GAME_COUNT_MINUS  = "Team1GameCountMinus";
    public static final String HOTKEY_TEAM2_GAME_COUNT_MINUS  = "Team2GameCountMinus";
    public static final String HOTKEY_TEAM3_GAME_COUNT_MINUS  = "Team3GameCountMinus";
    public static final String HOTKEY_TEAM1_MATCH_COUNT_PLUS  = "Team1MatchCountPlus";
    public static final String HOTKEY_TEAM2_MATCH_COUNT_PLUS  = "Team2MatchCountPlus";
    public static final String HOTKEY_TEAM3_MATCH_COUNT_PLUS  = "Team3MatchCountPlus";
    public static final String HOTKEY_TEAM1_MATCH_COUNT_MINUS = "Team1MatchCountMinus";
    public static final String HOTKEY_TEAM2_MATCH_COUNT_MINUS = "Team2MatchCountMinus";
    public static final String HOTKEY_TEAM3_MATCH_COUNT_MINUS = "Team3MatchCountMinus";
    public static final String HOTKEY_TEAM1_TIME_OUT_PLUS     = "Team1TimeOutPlus";
    public static final String HOTKEY_TEAM2_TIME_OUT_PLUS     = "Team2TimeOutPlus";
    public static final String HOTKEY_TEAM3_TIME_OUT_PLUS     = "Team3TimeOutPlus";
    public static final String HOTKEY_TEAM1_TIME_OUT_MINUS    = "Team1TimeOutMinus";
    public static final String HOTKEY_TEAM2_TIME_OUT_MINUS    = "Team2TimeOutMinus";
    public static final String HOTKEY_TEAM3_TIME_OUT_MINUS    = "Team3TimeOutMinus";
    public static final String HOTKEY_TEAM1_RESET             = "Team1Reset";
    public static final String HOTKEY_TEAM2_RESET             = "Team2Reset";
    public static final String HOTKEY_TEAM3_RESET             = "Team3Reset";
    public static final String HOTKEY_TEAM1_WARN              = "Team1Warn";
    public static final String HOTKEY_TEAM2_WARN              = "Team2Warn";
    public static final String HOTKEY_TEAM3_WARN              = "Team3Warn";
    public static final String HOTKEY_TEAM1_KING_SEAT         = "Team1KingSeat";
    public static final String HOTKEY_TEAM2_KING_SEAT         = "Team2KingSeat";
    public static final String HOTKEY_TEAM3_KING_SEAT         = "Team3KingSeat";
    public static final String HOTKEY_START_MATCH             = "StartMatch";
    public static final String HOTKEY_PAUSE_MATCH             = "PauseMatch";
    public static final String HOTKEY_END_MATCH               = "EndMatch";
    public static final String HOTKEY_START_GAME              = "StartGame";
    public static final String HOTKEY_SHOT_TIMER              = "ShotTimer";
    public static final String HOTKEY_PASS_TIMER              = "PassTimer";
    public static final String HOTKEY_TIME_OUT_TIMER          = "TimeOutTimer";
    public static final String HOTKEY_GAME_TIMER              = "GameTimer";
    public static final String HOTKEY_RECALL_TIMER            = "RecallTimer";
    public static final String HOTKEY_RESET_TIMERS            = "ResetTimers";
    public static final String HOTKEY_CLEAR_ALL               = "ClearAll";
    public static final String HOTKEY_UNDO                    = "Undo";
    public static final String HOTKEY_REDO                    = "Redo";
    public static final String HOTKEY_SHOW_SKUNK              = "ShowSkunk";
    public static final String HOTKEY_START_STREAM            = "StartStream";
    public static final String HOTKEY_CLEAR_TOURNAMENT        = "ClearTournament";
    public static final String HOTKEY_SWITCH_MATCH_COUNTS     = "SwitchMatchCounts";
    public static final String HOTKEY_SWITCH_SIDES            = "SwitchSides";
    public static final String HOTKEY_SWITCH_TEAMS            = "SwitchTeams";
    public static final String HOTKEY_SWITCH_SCORES           = "SwitchScores";
    public static final String HOTKEY_SWITCH_GAME_COUNTS      = "SwitchGameCounts";
    public static final String HOTKEY_SWITCH_TIME_OUTS        = "SwitchTimeOuts";
    public static final String HOTKEY_SWITCH_RESET_WARNS      = "SwitchResetWarns";
    public static final String HOTKEY_SWITCH_FORWARDS         = "SwitchForwards";
    public static final String HOTKEY_SWITCH_GOALIES          = "SwitchGoalies";
    public static final String HOTKEY_RESET_NAMES             = "ResetNames";
    public static final String HOTKEY_RESET_SCORES            = "ResetScores";
    public static final String HOTKEY_RESET_GAME_COUNTS       = "ResetGameCounts";
    public static final String HOTKEY_RESET_TIME_OUTS         = "ResetTimeOuts";
    public static final String HOTKEY_RESET_RESET_WARN        = "ResetResetWarn";
    public static final String HOTKEY_RESET_ALL               = "ResetAll";
    public static final String HOTKEY_RESET_MATCH_COUNTS      = "ResetMatchCounts";
    public static final String HOTKEY_CONNECT                 = "Connect";
    public static final String HOTKEY_DISCONNECT              = "Disconnect";
    public static final String HOTKEY_PUSH                    = "Push";
    public static final String HOTKEY_PULL                    = "Pull";
    public static final String HOTKEY_SHOW_SCORES             = "ShowScores";
    public static final String HOTKEY_SHOW_TIMER              = "ShowTimer";
    public static final String HOTKEY_SHOW_CUTTHROAT          = "ShowCutthroat";
    public static final String HOTKEY_AUTO_SCORE_CONNECT      = "AutoScoreMainConnect";
    public static final String HOTKEY_AUTO_SCORE_DISCONNECT   = "AutoScoreMainDisconnect";
    public static final String HOTKEY_AUTO_SCORE_SETTINGS     = "AutoScoreMainSettings";
    public static final String HOTKEY_BASE_SCRIPT             = "HotKeyBaseScript";
    public static final String HOTKEY_SCRIPT_PATH             = "HotKeyScriptPath";

    // ==================== PartnerProgram Keys ====================
    public static final String PP_PATH                  = "PartnerProgramPath";
    public static final String PP_PLAYER1_FILE          = "Player1FileName";
    public static final String PP_PLAYER2_FILE          = "Player2FileName";
    public static final String PP_PLAYER3_FILE          = "Player3FileName";
    public static final String PP_PLAYER4_FILE          = "Player4FileName";
    public static final String PP_EVENT_FILE            = "EventFileName";
    public static final String PP_TOURNAMENT_FILE       = "TournamentFileName";

    // ==================== AutoScore Settings Keys ====================
    public static final String AS_SERVER_ADDRESS        = "AutoScoreSettingsServerAddress";
    public static final String AS_SERVER_PORT           = "AutoScoreSettingsServerPort";
    public static final String AS_AUTO_CONNECT          = "AutoScoreSettingsAutoConnect";
    public static final String AS_DETAIL_LOG            = "AutoScoreSettingsDetailLog";

    // ==================== API Keys ====================
    public static final String API_ENABLED              = "APIEnabled";
    public static final String API_PORT                 = "APIPort";
    public static final String API_KEY                  = "APIKey";
}
