package finalProject;
import java.time.LocalDateTime;
import java.util.*;

// Player Class
class Player {
    private String name;
    private int age;
    private int runs;
    private int wickets;
    private int catches;

    public Player(String name, int age) {
        this.name = name;
        this.age = age;
        this.runs = 0;
        this.wickets = 0;
        this.catches = 0;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public int getRuns() {
        return runs;
    }

    public void addRuns(int runs) {
        this.runs += runs;
    }

    public int getWickets() {
        return wickets;
    }

    public void addWickets(int wickets) {
        this.wickets += wickets;
    }

    public int getCatches() {
        return catches;
    }

    public void addCatches(int catches) {
        this.catches += catches;
    }

    @Override
    public String toString() {
        return "Player{name='" + name + "', age=" + age + ", runs=" + runs + ", wickets=" + wickets + ", catches=" + catches + "}";
    }

	public void setRuns(int runs2) {
		// TODO Auto-generated method stub
		
	}
}

// Team Class
class Team {
    private String name;
    private List<Player> players;

    public Team(String name) {
        this.name = name;
        this.players = new ArrayList<>();
    }

    public void addPlayer(Player player) {
        players.add(player);
    }

    public String getName() {
        return name;
    }

    public List<Player> getPlayers() {
        return players;
    }

    @Override
    public String toString() {
        return "Team{name='" + name + "', players=" + players + "}";
    }
}

// Venue Class
class Venue {
    private String name;
    private String location;

    public Venue(String name, String location) {
        this.name = name;
        this.location = location;
    }

    public String getName() {
        return name;
    }

    public String getLocation() {
        return location;
    }

    @Override
    public String toString() {
        return "Venue{name='" + name + "', location='" + location + "'}";
    }
}

// Referee Class
class Referee {
    private String name;
    private int age;

    public Referee(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    @Override
    public String toString() {
        return "Referee{name='" + name + "', age=" + age + "}";
    }
}

// Match Class
class Match {
    private Team team1;
    private Team team2;
    private LocalDateTime dateTime;
    private Venue venue;
    private Referee referee;

    public Match(Team team1, Team team2, LocalDateTime dateTime, Venue venue, Referee referee) {
        this.team1 = team1;
        this.team2 = team2;
        this.dateTime = dateTime;
        this.venue = venue;
        this.referee = referee;
    }

    public Team getTeam1() {
        return team1;
    }

    public Team getTeam2() {
        return team2;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public Venue getVenue() {
        return venue;
    }

    public Referee getReferee() {
        return referee;
    }

    @Override
    public String toString() {
        return "Match{team1=" + team1.getName() + ", team2=" + team2.getName() + ", dateTime=" + dateTime + ", venue=" + venue.getName() + ", referee=" + referee.getName() + "}";
    }
}

// MatchStats Class
class MatchStats {
    private Match match;
    private Map<Player, Integer> runs;
    private Map<Player, Integer> wickets;
    private Map<Player, Integer> catches;

    public MatchStats(Match match) {
        this.match = match;
        this.runs = new HashMap<>();
        this.wickets = new HashMap<>();
        this.catches = new HashMap<>();
    }

    public void addRuns(Player player, int runs) {
        this.runs.put(player, this.runs.getOrDefault(player, 0) + runs);
    }

    public void addWickets(Player player, int wickets) {
        this.wickets.put(player, this.wickets.getOrDefault(player, 0) + wickets);
    }

    public void addCatches(Player player, int catches) {
        this.catches.put(player, this.catches.getOrDefault(player, 0) + catches);
    }

    public int getRuns(Player player) {
        return this.runs.getOrDefault(player, 0);
    }

    public int getWickets(Player player) {
        return this.wickets.getOrDefault(player, 0);
    }

    public int getCatches(Player player) {
        return this.catches.getOrDefault(player, 0);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Match Stats for " + match + ":\n");
        for (Player player : match.getTeam1().getPlayers()) {
            sb.append(player.getName()).append(": Runs: ").append(getRuns(player)).append(", Wickets: ").append(getWickets(player)).append(", Catches: ").append(getCatches(player)).append("\n");
        }
        for (Player player : match.getTeam2().getPlayers()) {
            sb.append(player.getName()).append(": Runs: ").append(getRuns(player)).append(", Wickets: ").append(getWickets(player)).append(", Catches: ").append(getCatches(player)).append("\n");
        }
        return sb.toString();
    }
}

// Sponsor Class
class Sponsor {
    private String name;
    private String category; // "Premium", "Main Partner", or "Other"

    public Sponsor(String name, String category) {
        this.name = name;
        this.category = category;
    }

    public String getName() {
        return name;
    }

    public String getCategory() {
        return category;
    }

    @Override
    public String toString() {
        return "Sponsor{name='" + name + "', category='" + category + "'}";
    }
}

// TournamentName Class
class TournamentName {
    private String name;

    public TournamentName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "TournamentName{name='" + name + "'}";
    }
}

// Tournament Class
abstract class Tournament {
    protected List<Match> matches;
    protected List<Venue> venues;
    protected List<Referee> referees;
    protected List<Sponsor> sponsors;

    public Tournament() {
        this.matches = new ArrayList<>();
        this.venues = new ArrayList<>();
        this.referees = new ArrayList<>();
        this.sponsors = new ArrayList<>();
    }

    public abstract void generateMatches(List<Team> teams);

    public void displaySchedule() {
        for (Match match : matches) {
            System.out.println("Day " + match.getDateTime().getDayOfMonth() + ", Match: " + match.getTeam1().getName() + " vs " + match.getTeam2().getName() +
                               ", Date/Time: " + match.getDateTime() + ", Venue: " + match.getVenue().getName() + ", Referee: " + match.getReferee().getName());
        }
    }

    public void addVenue(Venue venue) {
        venues.add(venue);
    }

    public void addReferee(Referee referee) {
        referees.add(referee);
    }

    public void addSponsor(Sponsor sponsor) {
        sponsors.add(sponsor);
    }

    public void displaySponsors() {
        for (Sponsor sponsor : sponsors) {
            System.out.println("Sponsor: " + sponsor.getName() + " (" + sponsor.getCategory() + ")");
        }
    }

    public String getMainSponsor() {
        for (Sponsor sponsor : sponsors) {
            if (sponsor.getCategory().equalsIgnoreCase("Premium")) {
                return sponsor.getName();
            }
        }
        return "Our Sponsors";
    }
}

// RoundRobinTournament Class
class RoundRobinTournament extends Tournament {
    @Override
    public void generateMatches(List<Team> teams) {
        for (int i = 0; i < teams.size(); i++) {
            for (int j = i + 1; j < teams.size(); j++) {
                Venue venue = venues.get(new Random().nextInt(venues.size()));
                Referee referee = referees.get(new Random().nextInt(referees.size()));
                LocalDateTime dateTime = LocalDateTime.now().plusDays(i).plusHours(j);
                matches.add(new Match(teams.get(i), teams.get(j), dateTime, venue, referee));
            }
        }
    }
}

// PrizeDistributor Class
class PrizeDistributor {
    public void distributePrizes(Team winner, Team runnerUp, List<Player> topBatsmen, List<Player> topBowlers) {
        System.out.println("Winner: " + winner.getName());
        System.out.println("Runner-Up: " + runnerUp.getName());

        System.out.println("\nTop 5 Batsmen:");
        for (int i = 0; i < 5 && i < topBatsmen.size(); i++) {
            Player batsman = topBatsmen.get(i);
            System.out.println("- " + batsman.getName() + ": " + batsman.getRuns() + " runs");
        }

        System.out.println("\nTop 5 Bowlers:");
        for (int i = 0; i < 5 && i < topBowlers.size(); i++) {
            Player bowler = topBowlers.get(i);
            System.out.println("- " + bowler.getName() + ": " + bowler.getWickets() + " wickets");
        }
    }
}

public class TournamentOrganizeManagementSystem {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Input tournament name
        System.out.println("Enter the name of the tournament:");
        String tournamentName = scanner.nextLine();
        @SuppressWarnings("unused")
		TournamentName tournament = new TournamentName(tournamentName);

        // Input sponsors
        List<Sponsor> sponsors = new ArrayList<>();
        System.out.println("\nEnter number of sponsors:");
        int numSponsors = scanner.nextInt();
        scanner.nextLine();  // Consume newline character after nextInt()

        for (int i = 0; i < numSponsors; i++) {
            System.out.println("Enter sponsor " + (i + 1) + " name:");
            String sponsorName = scanner.nextLine();
            System.out.println("Enter sponsor " + (i + 1) + " category (Premium/Main Partner/Other):");
            String sponsorCategory = scanner.nextLine();
            sponsors.add(new Sponsor(sponsorName, sponsorCategory));
        }

        // Input teams and players
        List<Team> teams = new ArrayList<>();
        System.out.println("Enter number of teams:");
        int numTeams = scanner.nextInt();
        scanner.nextLine();  // Consume newline character after nextInt()

        for (int i = 0; i < numTeams; i++) {
            System.out.println("Enter name of team " + (i + 1) + ":");
            String teamName = scanner.nextLine();
            Team team = new Team(teamName);

            System.out.println("Enter number of players for " + teamName + ":");
            int numPlayers = scanner.nextInt();
            scanner.nextLine();  // Consume newline character after nextInt()

            for (int j = 0; j < numPlayers; j++) {
                System.out.println("Enter player " + (j + 1) + " name:");
                String playerName = scanner.nextLine();
                team.addPlayer(new Player(playerName, 20)); // Assuming age is 20 for simplicity
            }

            teams.add(team);
        }

        // Input referees
        List<Referee> referees = new ArrayList<>();
        System.out.println("\nEnter number of referees:");
        int numReferees = scanner.nextInt();
        scanner.nextLine();  // Consume newline character after nextInt()

        for (int i = 0; i < numReferees; i++) {
            System.out.println("Enter referee " + (i + 1) + " details (name age):");
            String refereeName = scanner.next();
            int refereeAge = scanner.nextInt();
            referees.add(new Referee(refereeName, refereeAge));
            scanner.nextLine();  // Consume newline character after nextInt()
        }

        // Create venues
        List<Venue> venues = new ArrayList<>();
        System.out.println("\nEnter number of venues:");
        int numVenues = scanner.nextInt();
        scanner.nextLine();  // Consume newline character after nextInt()

        for (int i = 0; i < numVenues; i++) {
            System.out.println("Enter venue " + (i + 1) + " name:");
            String venueName = scanner.nextLine();
            venues.add(new Venue(venueName, "City" + (i + 1))); // Assuming city naming convention
        }

        // Create round-robin tournament
        RoundRobinTournament roundRobinTournament = new RoundRobinTournament();
//        for (Venue venue : venues) {
//            roundRobinTournament.addVenue(venue);
//        }
        
        for (int i=0; i<venues.size(); i++) {
            roundRobinTournament.addVenue(venues.get(i));
        }

//        for (Referee referee : referees) {
//            roundRobinTournament.addReferee(referee);
//        }
        
        for (int i=0; i<referees.size(); i++) {
            roundRobinTournament.addReferee(referees.get(i));
        }

//        for (Sponsor sponsor : sponsors) {
//            roundRobinTournament.addSponsor(sponsor);
//        }
        
        for (int i=0; i<sponsors.size(); i++) {
            roundRobinTournament.addSponsor(sponsors.get(i));
        }

        // Display tournament sponsor
        System.out.println("\nTOURNAMENT BROUGHT TO YOU BY: " + roundRobinTournament.getMainSponsor().toUpperCase() + "\n");

        // Generate matches
        roundRobinTournament.generateMatches(teams);

        // Display match fixtures
        System.out.println("\nMatch Fixtures:");
        roundRobinTournament.displaySchedule();

        // Input runs and wickets for each player
        for (Team team : teams) {
            for (Player player : team.getPlayers()) {
                System.out.println("\nEnter runs scored by " + player.getName() + ":");
                int runs = scanner.nextInt();
                player.addRuns(runs);

                System.out.println("Enter wickets taken by " + player.getName() + ":");
                int wickets = scanner.nextInt();
                player.addWickets(wickets);
            }
        }

        // Determine tournament winner and runner-up
        System.out.println("\nEnter the name of the winner of the tournament:");
        String winnerName = scanner.next();
        Team winner = null;
        for (Team team : teams) {
            if (team.getName().equalsIgnoreCase(winnerName)) {
                winner = team;
                break;
            }
        }

        System.out.println("Enter the name of the runner-up of the tournament:");
        String runnerUpName = scanner.next();
        Team runnerUp = null;
        for (Team team : teams) {
            if (team.getName().equalsIgnoreCase(runnerUpName)) {
                runnerUp = team;
                break;
            }
        }

        // Determine top 5 batsmen
        List<Player> topBatsmen = new ArrayList<>(50); // assuming we have more players in list
//        for (Team team : teams) {
//            for (Player player : team.getPlayers()) {
//                if (topBatsmen.size() < 5) {
//                    topBatsmen.add(player);
//                } else {
//                    for (int i = 0; i < 5; i++) {
//                        if (player.getRuns() > topBatsmen.get(i).getRuns()) {
//                            topBatsmen.set(i, player);
//                            break;
//                        }
//                    }
//                }
//            }
//        }
        
        List<Player> allPlayers = new ArrayList<>();
        
        for (Team team : teams) {
          for (Player player : team.getPlayers()) {
              allPlayers.add(player);
          }
        }
        
        for (int i = 0; i< allPlayers.size(); i++) {
        	for (int j=0; j< allPlayers.size() - i - 1; j++) {
        		if(allPlayers.get(j).getRuns() < allPlayers.get(j+1).getRuns()) {
        			Player temp = allPlayers.get(j);
        			allPlayers.set(j,  allPlayers.get(j+1));
        			allPlayers.set(j+1, temp);
        		}
        	}
        }
        
        for (int i = 0; i<5; i++) {
        	 topBatsmen.add(allPlayers.get(i));
        }
        

        // Determine top 5 bowlers
        List<Player> topBowlers = new ArrayList<>(50); // assuming we have more players in list
//        for (Team team : teams) {
//            for (Player player : team.getPlayers()) {
//                if (topBowlers.size() < 5) {
//                    topBowlers.add(player);
//                } else {
//                    for (int i = 0; i < 5; i++) {
//                        if (player.getWickets() > topBowlers.get(i).getWickets()) {
//                            topBowlers.set(i, player);
//                            break;
//                        }
//                    }
//                }
//            }
//        }
        
        
        for (int i = 0; i< allPlayers.size(); i++) {
        	for (int j=0; j< allPlayers.size() - i - 1; j++) {
        		if(allPlayers.get(j).getWickets() < allPlayers.get(j+1).getWickets()) {
        			Player temp = allPlayers.get(j);
        			allPlayers.set(j,  allPlayers.get(j+1));
        			allPlayers.set(j+1, temp);
        		}
        	}
        }
        
        for (int i = 0; i<5; i++) {
        	topBowlers.add(allPlayers.get(i));
        }

        // Display tournament results
        PrizeDistributor prizeDistributor = new PrizeDistributor();
        prizeDistributor.distributePrizes(winner, runnerUp, topBatsmen, topBowlers);

        // Close input scanner
        scanner.close();
    }
}
