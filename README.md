https://en.wikipedia.org/wiki/Breakout_(video_game)

<br><br>

#### How to compile the com.example.dmscoursework.code to produce the application

To compile using Maven

-   Method 1 - Open project in IntelliJ - On the right there is a little sideways bar, saying ‘Maven’. Click on Maven, then click on the little m "Execute maven goal". Type mvn clean javafx:run.
-   Method 2 - Open Command Prompt, navigate to project folder. Type mvn clean javafx:run.
    <br><br>

#### Where your Javadoc documentation is stored (the path to the directory)

-   Note: view is in javaDocs need to search for it manually.
-   C:\Users\wilva\Desktop\University\Maintainable Software\CW\dms-coursework\javadocs
-   Relative path is just : javadocs
    <br><br>

#### A list of features that are implemented and are working properly,

-   Increase Ball Count. In the DebugMenu ball count can be increased and decreased.
-   Level Selector - Images of levels are displayed correctly, and updated to the correct images if the user decides to change the theme.
-   Level Selector - Selecting a level. The level selector works properly as users can select any level without any issues.
-   AI. The AI works properly as it will track the ball and after a specified time not hitting a brick, will assume there is an issue with how the ball is pathing, and drop the ball intentionally. The AI is able to beat all the levels with the ball count of three.
-   Music Settings Controller - Users can change music volume and go to the next song, previous song, reset song.
-   Music Controller - There is only one instance, it being a singleton, so the music will be the same no matter the screen the user is on.
-   Scoreboard - works correctly in that it shows the top 10 players, their usernames, scores and time taken for each to complete the game.
    <br><br>

#### A list of features that are implemented and are not working properly,

-   PauseMenu and the DebugMenu - If I open the debugConsole using SHIFT + ALT + F1, I can open another debugConsole again on top. The same goes for the PauseMenu when I press ESCAPE.
-   Paddle and Ball Issue - if ball hits paddle at a perfect angle on the edge, it stick to paddle travelling along a horizontal direction.
-   Brick and Ball Issue - if ball hits brick at perfect angle on the edge it will instantly break break even at strength 2 and continue to hit the next brick.
    <br><br>

#### A list of features, if any, that are not implemented with an explanation of why they were not implemented,

-   I wanted to allow users to save their level or have the game automatically save when they complete a level, unfortunately did not get the chance to implement as I ran out of time.
-   I wanted to add another debug panel feature where users can also choose to go back to the previous level. Again, I did not implement as I ran out of time.
    <br><br>

#### A list of new Java classes that you introduced for the assignment,

-   BasicPaddle, BluePaddle, OrangePaddle, PinkPaddle.
-   CandyCaneLevel, CandyDrinkLevel, LollipopLevel, FishHookLevel, FishLevel, WaveLevel, FireTreeLevel, OrangeFoodLevel, SunLevel. ChessBoardLevel, SingleTypeLevel, CollisionChecker, LevelBuilder, LevelManager.
-   BlueBrick, CheckerBlueBrick, StripeBlueBrick, OrangeBrick, CheckerOrangeBrick, StripeOrangeBrick, PinkBrick, CheckerPinkBrick, StripePinkBrick.
-   PauseMenu, ScorePopup,
-   BlueBall, OrangeBall, PinkBall, RubberBall
-   ScoreReader, ScoreWriter, SortTextFile,
-   AiController, DebugController, GameController, MusicController, PauseController, LevelSelector, MusicScreen, Scoreboard, StartController
    <br><br>

#### A list of Java classes that you modified from the given com.example.dmscoursework.code base,

-   Ball
-   Ball1
-   Brick
-   Brick1
-   Brick2
-   Brick3
-   DebugConsole
-   DebugPanel
-   GameBoard
-   GameFrame
-   Paddle
-   StartGame
-   Wall
