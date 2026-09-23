void main() {
    boolean systemIsRunning = true;
    int order = 1;
    boolean accessGranted = true;
    int moviePrice = 105;
    theaterlist();
    List<boolean[][]> movieTheater = theaterlist();

//bb
    while (systemIsRunning) {
        String[] movieNames = {"Star Wars - Roque one", "Cocaine Bear", "The Odyssey"}; // Angivede film
        menu(); // Strings med menu-punkter

        int menuPressed = Integer.parseInt(IO.readln("Hvor vil du hen idag? Vælg mellem 1-3 "));

        if (menuPressed == 1) {
            for (int I = 1; I < 4; I++) { // Adgangskode validering
                boolean correctPassword = IO.readln("Angiv adganskode: ").equalsIgnoreCase("Alphahyde54"); // angivet "korrekt" adgangskode
                if (correctPassword) {
                    accessGranted = true;
                    break;
                } else if (I == 3) {
                    IO.println("Adgangskode indtastet forkert for mange gange");
                    accessGranted = false;
                    break;
                } else {
                    IO.println("Adgangskode er forkert");
                }
            }

            if(!accessGranted) { // hvis adgangskode er forkert, slukker den hele koden
                systemIsRunning = false;
                continue;
            }
            IO.println("\nVelkommen til Admin menuen");
            IO.println("1. Rediger filmliste\n2. Ret billet pris\n3. Rediger biografsal");
            int adminMenuPressed = Integer.parseInt(IO.readln("Hvor vil du hen idag? Vælg mellem 1-3 "));

            if (adminMenuPressed == 1) { //film redigering.
                filmListe(movieNames, order);

                int chosenFilm = Integer.parseInt(IO.readln("\nHvilken film vil du redigere? "));
                movieNames[chosenFilm - 1] = IO.readln("\nHvad er den nye film du vil erstatte med?\n");
                IO.println("\nDin film er nu tilføjet, og dette er din nuværende film liste:");

                order = 1;
                for (String everyFilm : movieNames) {
                    IO.println(order + ". " + everyFilm);
                    order++;
                }
            } else if (adminMenuPressed == 2) { // Pris redigering
                IO.println("\nDen nuværende billetpris er: " + moviePrice + "kr.");
                moviePrice = Integer.parseInt(IO.readln("\nHvilken ny billet pris vil du angive? "));
                IO.println("\nDen nye billetpris pr stk. er: " + moviePrice);
            } else if (adminMenuPressed == 3) { // Film størrelses redigering
                IO.println();
                int roomNum = 1;

                for (boolean[][] theaterRooms : movieTheater) { // for-loop for at hente rows og sæder i alle sale
                    int rows = theaterRooms.length;
                    int totalSeats = 0;
                    for (boolean[] row : theaterRooms) {
                        totalSeats += row.length;
                    }

                    IO.println(String.format("Sal %d. Rækker: %d Sæder: %d", roomNum, rows, totalSeats));
                    roomNum++;
                }

                int adminChosenRoom = Integer.parseInt(IO.readln("Hvilken sal vil du gerne redigere? "));
                boolean[][] roomToEdit = editTheaterRoom(adminChosenRoom); // Funktion til at redigere film sal.
                movieTheater.set(adminChosenRoom,roomToEdit);

            } else {
                String backOrExit = IO.readln("\nTryk Enter for at komme tilbage, eller (e) for at afslutte ");
                systemIsRunning = back(systemIsRunning, backOrExit);
            }


            String backOrExit = IO.readln("\nTryk Enter for at komme tilbage, eller (e) for at afslutte ");
            systemIsRunning = back(systemIsRunning, backOrExit);

        }
        if (menuPressed == 2) {
            filmListe(movieNames, order);
            int userChosenFilm = Integer.parseInt(IO.readln("Hvilken film vil du se? \n"));
            boolean[][] chosenFilm = movieTheater.get(userChosenFilm-1); // vælger specifik sal, til den angivede film at kunden vil se.
            IO.print("\n                        " + movieNames[userChosenFilm-1] + " \n");
           // int antalBilletter = Integer.parseInt(IO.readln("Hvor mange billetter vil du bestille? "));
            String bookedSeat = bookSeat(chosenFilm);
            IO.println("Du har nu booket; " + bookedSeat);
            String backOrExit = IO.readln("\nTryk Enter for at komme tilbage, eller (e) for at afslutte");
            systemIsRunning = back(systemIsRunning, backOrExit);
        }
        if (menuPressed == 3) {
            IO.println("God dag :)");
            systemIsRunning = false;
        }
    }
}



List <boolean[][]> theaterlist() {
    List <boolean[][]> movieTheater = new ArrayList<>();
    boolean[][] theater1 = new boolean[5][8];
    boolean[][] theater2 = new boolean[5][8];
    boolean[][] theater3 = new boolean[5][8];
    movieTheater.add(theater1);
    movieTheater.add(theater2);
    movieTheater.add(theater3);
    return movieTheater;
}

boolean[][] editTheaterRoom(int adminChosenRoom) {
    int adminEditrow = Integer.parseInt(IO.readln("Hvor mange rækker skal salen have? "));
    int adminEditamountseats = Integer.parseInt(IO.readln("Hvor mange rækker skal salen have? "));
    boolean[][] roomToEdit = new boolean[adminEditrow][adminEditamountseats];
    IO.println(String.format("""
                            Sal %d. har nu følgende layout:
                            Rækker: %d
                            Antal sæder: %d
                            """, adminChosenRoom, adminEditrow, adminEditamountseats));
   return roomToEdit;
}


boolean back(boolean systemIsRunning, String backOrExit) {
    if (backOrExit.equals("e")) {
        IO.println("God dag :)");
        systemIsRunning = false;
    }
    return systemIsRunning;
}

String filmListe (String[] movieNames, int order) {
    String liste = "";
    for (String everyFilm : movieNames) {
        IO.println(order + ". " + everyFilm);
        order++;
    }
    order = 1;
    return liste;
}

void menu() {
    IO.println("Biograf Menu");
    IO.println("1. Admin Menu");
    IO.println("2. Book seats");
    IO.println("3. Exit");
}

String bookSeat(boolean[][] chosenFilm) {
    IO.print("\n                              -lærred- \n\n\n");
    String booked = "|x|";
    String unbooked = "|_|";


    for (int i = 0; i < chosenFilm.length; i++) {
        for (int j = 0; j < chosenFilm[i].length; j++) {
            if (chosenFilm[i][j]) {
                IO.print("\t" + booked + "\t");
            } else {
                IO.print("\t" + unbooked + "\t");
            }
        }
        IO.println("");
    }

    boolean vacantSeatPicked = false;
    boolean bookingProcess = true;
    String bookedSeat = "";

    while (bookingProcess) {
        while (!vacantSeatPicked) {
            int chosenRow = Integer.parseInt(IO.readln("Hvilken række ønsker du at vælge?"));
            int chosenCollumn = Integer.parseInt(IO.readln("Hvilken Sæde ønsker du at vælge? Fra venstre**"));

            chosenRow -= 1;
            chosenCollumn -= 1;
            if (chosenFilm[chosenRow][chosenCollumn]) {
                IO.println("Dette sæde er desværre allerede booket, vælg venligst et nyt");
            } else {
                chosenFilm[chosenRow][chosenCollumn] = true;
                IO.print("\n                              -lærred- \n\n\n");
                for (int i = 0; i < chosenFilm.length; i++) {
                    for (int j = 0; j < chosenFilm[i].length; j++) {
                        if (chosenFilm[i][j]) {
                            IO.print("\t" + booked + "\t");
                        } else {
                            IO.print("\t" + unbooked + "\t");
                        }
                    }
                    IO.println("");
                }
                vacantSeatPicked = true;
                if (IO.readln("\nVil du bestille flere billetter? j/n ").equalsIgnoreCase("n")) {
                    bookingProcess = false;
                } else {
                    vacantSeatPicked = false;
                }
                bookedSeat += "\nrække " + (chosenRow + 1) + ", sæde " + (chosenCollumn + 1);
            }
        }

    }
    return bookedSeat;
}