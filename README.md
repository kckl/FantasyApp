<h2> NBA Fantasy Helper </h2>

For all *NBA fantasy lovers*, this tool can be used to help with drafting, analyzing, and visualizing how your team is doing relative to the rest of the league. 
Personally, as someone who enjoys playing fantasy and watching the NBA, I'd be interested in implementing this tool for future seasons and to ultimately, win the league!

#### Main Functionality:
- Create an NBA fantasy team, then input each player's information (eg. name, per game statistics) to build out a fantasy team
- Using a 5 category format, the tool will show how the team's performance in each statistical category
- Add multiple teams to the league to show a leaderboard
- Draft multiple players to a team

#### User Stories: 
- As a user, I want to be able to create a new team for my league
- As a user, I want to be able to remove a team from my league
- As a user, I want to be able to select a team, and add a new player to the team
- As a user, I want to be able to add a player's per game statistics
- As a user, I want to be able to select a team in the league and list out all the players on the team
- As a user, I want to be able to view my league settings, and update or change them
- As a user, I want to be able to save my fantasy league to file
- As a user, I want to be able to load my fantasy league from file

#### Phase 4: Task 2
Fri Nov 19 16:05:00 PST 2021
Loaded league from file

Fri Nov 19 16:05:02 PST 2021
Team1 registered to league

Fri Nov 19 16:05:07 PST 2021
Team2 registered to league

Fri Nov 19 16:05:20 PST 2021
CURRY added to Team1's team

Fri Nov 19 16:05:47 PST 2021
LUKA added to Team2's team

Fri Nov 19 16:06:03 PST 2021
League renamed to: NBA Fantasy League

Fri Nov 19 16:06:03 PST 2021
League resized to: 12

Fri Nov 19 16:06:07 PST 2021
Saved league to file

#### Phase 4: Task 3
- There is a fair bit of duplicated code in the GUI classes, such as ViewLeaguePanel and ViewTeamPanel. Instead, I could introduce a new abstract class and have the ViewLeaguePanel and ViewTeamPanel classes extend this class instead. This way, each time I want to create a new panel for my GUI, I can extend this new class instead of duplicating a lot of the code.
- Make all of my methods robust. Eg. ViewLeaguePanel.selectTeamPopUp() could be made robust so that the requires clause is not necessary.
- 