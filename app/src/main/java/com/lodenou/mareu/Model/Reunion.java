package com.lodenou.mareu.Model;





public class Reunion {



    // id de la reunion
    private String idReu;

    // heure de la reunion
    private String timeReu;

    // Salle de reunion
    private String roomReu;

    // Email des participants
    private String emailReu;


    public Reunion(String idReu, String timeReu, String roomReu, String emailReu) {
        this.idReu = idReu;
        this.timeReu = timeReu;
        this.roomReu = roomReu;
        this.emailReu = emailReu;
    }

    // getter setter @idReu
    public String getIdReu() {
        return idReu;
    }
    public void setIdReu(String idReu) {
        this.idReu = idReu;
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
