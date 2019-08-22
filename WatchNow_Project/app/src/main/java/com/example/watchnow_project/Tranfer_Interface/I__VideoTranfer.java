package com.example.watchnow_project.Tranfer_Interface;

import com.example.watchnow_project.Model.entity.Video;

import java.util.ArrayList;

public interface I__VideoTranfer {
    void sendVideo(ArrayList<Video> videos);
    void receiveVideo(boolean x);
    void sendString(String x);
}
