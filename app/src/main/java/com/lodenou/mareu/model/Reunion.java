package com.lodenou.mareu.model;





public class Reunion {


    private String subjectReu;

    // heure de la reunion
    private String timeReu;

    // Salle de reunion
    private String roomReu;

    // Email des participants
    private String emailReu;


    public Reunion(String subjectReu, String timeReu, String roomReu, String emailReu) {

        this.subjectReu = subjectReu;
        this.timeReu = timeReu;
        this.roomReu = roomReu;
        this.emailReu = emailReu;
    }


    public String getSubjectReu() {
        return subjectReu;
    }

    public void setSubjectReu(String subjectReu) {
        this.subjectReu = subjectReu;
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
