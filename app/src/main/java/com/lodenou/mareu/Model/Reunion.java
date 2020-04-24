package com.lodenou.mareu.Model;





public class Reunion {




    // heure de la reunion
    private String timeReu;

    // Salle de reunion
    private String roomReu;

    // Email des participants
    private String emailReu;


    public Reunion(String timeReu, String roomReu, String emailReu) {

        this.timeReu = timeReu;
        this.roomReu = roomReu;
        this.emailReu = emailReu;
    }



    // getter setter @timeReu
    public String getTimeReu() {
        return timeReu;
    }
    public void setTimeReu(String timeReu) {
        this.timeReu = timeReu;
    }


    // getter setter @roomReu
    public String getRoomReu() {
        return roomReu;
    }
    public void setRoomReu(String roomReu) {
        this.roomReu = roomReu;
    }


    // getter setter @emailReu
    public String getEmailReu() {
        return emailReu;
    }
    public void setEmailReu(String emailReu) {
        this.emailReu = emailReu;
    }


}
