void main() {
    boolean systemIsRunning = true;
    int order = 1;
    while (systemIsRunning) {
        String[] movieNames = {"Star Wars - Roque one", "Dune 2", "Spider-Man, Brand new day", "Cocaine Bear", "The Odyssey"};
        menu();

        int menuPressed = Integer.parseInt(IO.readln("Hvor vil du hen idag? Vælg mellem 1-3 "));

        if (menuPressed == 1) {
            Boolean correctPassword = IO.readln("Angiv adganskode: ").equalsIgnoreCase("Alphahyde54");
            for (int I = 0; I < 4; I++) { // This shit doesn't work. Tror det er et problem med {}
                if (correctPassword) {
                    break;
                } else if (I == 3) {
                    IO.println("Adgangskode indtastet forkert for mange gange");
                    systemIsRunning = false;
                } else {
                    IO.println("Adgangskode er forkert");
                }
            }
                IO.println("Velkommen til Admin menuen");
                for (String everyFilm : movieNames) {
                    IO.println(order + ". " + everyFilm);
                    order++;
                }

                int chosenFilm = Integer.parseInt(IO.readln("\nHvilken film vil du redigere? "));
                movieNames[chosenFilm - 1] = IO.readln("\nHvad er den nye film du vil erstatte med?\n");
                IO.println("\nDin film er nu tilføjet, og dette er din nuværende film liste:");

                order = 1;
                for (String everyFilm : movieNames) {
                    IO.println(order + ". " + everyFilm);
                    order++;
                }
                String backOrExit = IO.readln("\nTryk Enter for at komme tilbage, eller (e) for at afslutte");
                back(systemIsRunning, backOrExit);
        }
        if (menuPressed == 2) {
            String bookedSeat = bookSeat();
            IO.println("Du har nu booket; " + bookedSeat);
            String backOrExit = IO.readln("\nTryk Enter for at komme tilbage, eller (e) for at afslutte");
            back(systemIsRunning, backOrExit);
        }
        if (menuPressed == 3) {
            IO.println("God dag :)");
            systemIsRunning = false;
        }
    }
}






boolean back(boolean systemRunning, String backOrExit) {
    if (backOrExit.equals("e")) {
        IO.println("God dag :)");
        systemRunning = false;
    }
    return systemRunning;
}

String filmListe (String[] movieNames) {
    String liste = "";
    for (String everyFilm : movieNames) {
        IO.println("\n-" + everyFilm);
    }
    return liste;
}

void menu() {
    IO.println("Biograf Menu");
    IO.println("1. Admin Menu");
    IO.println("2. Book seats");
    IO.println("3. Exit");
}

String bookSeat() {
    boolean[][] seats = new boolean[5][8];
    IO.print("\n                              -lærred- \n\n\n");
    String booked = "|x|";
    String unbooked = "|_|";
    seats[0][5] = true;
    seats[0][4] = true;


    for (int i = 0; i < seats.length; i++) {
        for (int j = 0; j < seats[i].length; j++) {
            if (seats[i][j]) {
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
            if (seats[chosenRow][chosenCollumn]) {
                IO.println("Dette sæde er desværre allerede booket, vælg venligst et nyt");
            } else {
                seats[chosenRow][chosenCollumn] = true;
                IO.print("\n                              -lærred- \n\n\n");
                for (int i = 0; i < seats.length; i++) {
                    for (int j = 0; j < seats[i].length; j++) {
                        if (seats[i][j]) {
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