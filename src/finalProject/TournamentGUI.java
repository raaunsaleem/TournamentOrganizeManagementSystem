package finalProject;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class TournamentGUI extends JFrame {
    private static final long serialVersionUID = 1L;
    private JTextField tournamentNameField;
    private JTextField sponsorNameField;
    private JTextArea console;
    private List<Team> teams;
    private List<Referee> referees;
    private List<Venue> venues;
    private List<Sponsor> sponsors;

    public TournamentGUI() {
        teams = new ArrayList<>();
        referees = new ArrayList<>();
        venues = new ArrayList<>();
        sponsors = new ArrayList<>();

        initializeUI();
    }

    private void initializeUI() {
        setTitle("Tournament Management System");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null);

        JPanel mainPanel = new JPanel(new BorderLayout());

        JPanel infoPanel = new JPanel(new GridLayout(2, 2));
        infoPanel.setBorder(BorderFactory.createTitledBorder("Tournament Information"));

        JLabel tournamentNameLabel = new JLabel("Tournament Name:");
        tournamentNameField = new JTextField();
        JLabel sponsorNameLabel = new JLabel("Sponsor Name:");
        sponsorNameField = new JTextField();

        infoPanel.add(tournamentNameLabel);
        infoPanel.add(tournamentNameField);
        infoPanel.add(sponsorNameLabel);
        infoPanel.add(sponsorNameField);

        console = new JTextArea(20, 50);
        console.setEditable(false);
        JScrollPane consoleScrollPane = new JScrollPane(console);

        JPanel controlPanel = new JPanel(new GridLayout(1, 2));

        JButton addTeamsButton = new JButton("Add Teams");
        addTeamsButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                addTeams();
            }
        });

        JButton generateMatchesButton = new JButton("Generate Matches");
        generateMatchesButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                generateMatches();
            }
        });

        controlPanel.add(addTeamsButton);
        controlPanel.add(generateMatchesButton);

        mainPanel.add(infoPanel, BorderLayout.NORTH);
        mainPanel.add(consoleScrollPane, BorderLayout.CENTER);
        mainPanel.add(controlPanel, BorderLayout.SOUTH);

        getContentPane().add(mainPanel);
    }

    private void addTeams() {
        String tournamentName = tournamentNameField.getText().trim();
        String sponsorName = sponsorNameField.getText().trim();

        if (tournamentName.isEmpty() || sponsorName.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter tournament name and sponsor name.");
            return;
        }

        console.append("Tournament Name: " + tournamentName + "\n");
        console.append("Sponsor Name: " + sponsorName + "\n\n");

        try {
            int numSponsors = Integer.parseInt(JOptionPane.showInputDialog(this, "Enter number of sponsors:"));
            for (int i = 0; i < numSponsors; i++) {
                String sponsor = JOptionPane.showInputDialog(this, "Enter sponsor " + (i + 1) + " name:");
                String category = JOptionPane.showInputDialog(this, "Enter sponsor " + (i + 1) + " category (Premium/Main Partner/Other):");
                sponsors.add(new Sponsor(sponsor, category));
            }

            int numTeams = Integer.parseInt(JOptionPane.showInputDialog(this, "Enter number of teams:"));
            for (int i = 0; i < numTeams; i++) {
                String teamName = JOptionPane.showInputDialog(this, "Enter name of team " + (i + 1) + ":");
                Team team = new Team(teamName);
                teams.add(team);

                int numPlayers = Integer.parseInt(JOptionPane.showInputDialog(this, "Enter number of players for " + teamName + ":"));
                for (int j = 0; j < numPlayers; j++) {
                    String playerName = JOptionPane.showInputDialog(this, "Enter name of player " + (j + 1) + " for " + teamName + ":");
                    Player player = new Player(playerName, 20);
                    team.addPlayer(player);
                }

                console.append("Team Name: " + teamName + "\n");
                console.append("Players: " + team.getPlayers() + "\n\n");
            }

            int numReferees = Integer.parseInt(JOptionPane.showInputDialog(this, "Enter number of referees:"));
            for (int i = 0; i < numReferees; i++) {
                String refereeName = JOptionPane.showInputDialog(this, "Enter referee " + (i + 1) + " name:");
                int refereeAge = Integer.parseInt(JOptionPane.showInputDialog(this, "Enter referee " + (i + 1) + " age:"));
                referees.add(new Referee(refereeName, refereeAge));
            }

            int numVenues = Integer.parseInt(JOptionPane.showInputDialog(this, "Enter number of venues:"));
            for (int i = 0; i < numVenues; i++) {
                String venueName = JOptionPane.showInputDialog(this, "Enter venue " + (i + 1) + " name:");
                venues.add(new Venue(venueName, venueName)); // Ensure Venue constructor matches
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Invalid input. Please enter valid numbers.");
        }
    }

    private void generateMatches() {
        if (teams.isEmpty() || referees.isEmpty() || venues.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please add teams, referees, and venues first.");
            return;
        }

        console.append("TOURNAMENT BROUGHT TO YOU BY: " + sponsors.get(0).getName() + "\n\n");
        console.append("Match Fixtures:\n");

        Random random = new Random();
        for (int i = 0; i < teams.size(); i++) {
            for (int j = i + 1; j < teams.size(); j++) {
                Team team1 = teams.get(i);
                Team team2 = teams.get(j);

                Venue venue = venues.get(random.nextInt(venues.size()));
                Referee referee = referees.get(random.nextInt(referees.size()));
                LocalDateTime dateTime = LocalDateTime.now().plusDays(i).plusHours(j);

                Match match = new Match(team1, team2, dateTime, venue, referee);
                console.append("Day " + (i + 1) + ", " + match + "\n");
            }
        }

        for (Team team : teams) {
            for (Player player : team.getPlayers()) {
                try {
                    int runs = Integer.parseInt(JOptionPane.showInputDialog(this, "Enter runs scored by " + player.getName() + ":"));
                    int wickets = Integer.parseInt(JOptionPane.showInputDialog(this, "Enter wickets taken by " + player.getName() + ":"));
                    player.setRuns(runs); // Corrected from player.runs = runs;
                    player.addWickets(wickets); // Corrected from player.wickets = wickets;
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(this, "Invalid input. Please enter valid numbers.");
                }
            }
        }

        String winner = JOptionPane.showInputDialog(this, "Enter the name of the winner of the tournament:");
        String runnerUp = JOptionPane.showInputDialog(this, "Enter the name of the runner-up of the tournament:");

        console.append("\nWinner: " + winner + "\n");
        console.append("Runner-Up: " + runnerUp + "\n\n");

        console.append("Top 5 Batsmen:\n");
        teams.stream()
                .flatMap(team -> team.getPlayers().stream())
                .sorted((p1, p2) -> Integer.compare(p2.getRuns(), p1.getRuns()))
                .limit(5)
                .forEach(player -> console.append("- " + player.getName() + ": " + player.getRuns() + " runs\n"));

        console.append("\nTop 5 Bowlers:\n");
        teams.stream()
                .flatMap(team -> team.getPlayers().stream())
                .sorted((p1, p2) -> Integer.compare(p2.getWickets(), p1.getWickets()))
                .limit(5)
                .forEach(player -> console.append("- " + player.getName() + ": " + player.getWickets() + " wickets\n"));
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new TournamentGUI().setVisible(true);
            }
        });
    }
}