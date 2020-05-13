package com.lodenou.mareu.model;


import java.util.Date;

public class Reunion {

    // Meeting' subject
    private String subjectReu;

    // heure de la reunion
    private Date timeReu;

    // Salle de reunion
    private String roomReu;

    // Email des participants
    private String emailReu;

    // Constructor
    public Reunion(String subjectReu, Date timeReu, String roomReu, String emailReu) {

        this.subjectReu = subjectReu;
        this.timeReu = timeReu;
        this.roomReu = roomReu;
        this.emailReu = emailReu;
    }

    // Getter and setter @subjectReu
    public String getSubjectReu() {
        return subjectReu;
    }

    public void setSubjectReu(String subjectReu) {
        this.subjectReu = subjectReu;
    }

    // Getter setter @timeReu
    public Date getTimeReu() {
        return timeReu;
    }
    public void setTimeReu(Date timeReu) {
        this.timeReu = timeReu;
    }


    // Getter setter @roomReu
    public String getRoomReu() {
        return roomReu;
    }
    public void setRoomReu(String roomReu) {
        this.roomReu = roomReu;
    }


    // Getter setter @emailReu
    public String getEmailReu() {
        return emailReu;
    }
    public void setEmailReu(String emailReu) {
        this.emailReu = emailReu;
    }

}
