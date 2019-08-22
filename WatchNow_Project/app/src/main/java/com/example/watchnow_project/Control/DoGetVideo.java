package com.example.watchnow_project.Control;

import android.os.AsyncTask;
import android.util.Log;

import com.example.watchnow_project.Model.entity.Video;
import com.example.watchnow_project.Tranfer_Interface.I__VideoTranfer;
import com.example.watchnow_project.View.HotVideoFragment;
import com.example.watchnow_project.View.MainActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

public class DoGetVideo extends AsyncTask<Void, Void, String> {
    private String resultAPI;


    private String url;
    ArrayList<Video> videos;
    private static final String TAG = "DoGetVideo";

    public DoGetVideo(String url) {
        this.url = url;
    }

    public String getResultAPI() {
        return resultAPI;
    }

    public ArrayList<Video> getVideos() {
        return videos;
    }

    @Override
    protected String doInBackground(Void... voids) {
        try {
            URL url = new URL(this.url);
            URLConnection connection = url.openConnection();
            InputStream is = connection.getInputStream();
            int byteCharacter;
            resultAPI = "";
            while ((byteCharacter = is.read()) != -1) {
                resultAPI += (char) byteCharacter;
            }
            Log.d(TAG, "doInBackground: " + resultAPI);
            return resultAPI;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        try {
            videos = new ArrayList<>();
            JSONArray hotvideoJSonArray = new JSONArray(s);

            for (int i = 0; i < hotvideoJSonArray.length(); i++) {
                JSONObject objectHotVideo = hotvideoJSonArray.getJSONObject(i);

                Video video = new Video();
                video.setId(objectHotVideo.getString("id"));
                video.setProvider_ID(objectHotVideo.getString("provider_id"));
                video.setCategory_ID(objectHotVideo.getString("category_id"));
                video.setTitle(objectHotVideo.getString("title"));
                video.setAvatar(objectHotVideo.getString("avatar"));
                video.setPrice(objectHotVideo.getDouble("price"));
                video.setStatus(objectHotVideo.getInt("status"));
                video.setDeleted(objectHotVideo.getInt("deleted"));
                video.setCopyRight(objectHotVideo.getString("copyright"));
                video.setArtist_Name(objectHotVideo.getString("artist_name"));
                video.setAlbum_Name(objectHotVideo.getString("album_name"));
                video.setFile_Mp4(objectHotVideo.getString("file_mp4"));
                video.setFile_Mp4_Size(objectHotVideo.getDouble("file_mp4_size"));
                video.setFile_3gp_Size(objectHotVideo.getDouble("file_3gp_size"));
                video.setTotal_Downloaded(objectHotVideo.getString("total_downloaded"));
                video.setDescription(objectHotVideo.getString("description"));
                video.setDuration(objectHotVideo.getString("duration"));
                video.setDate_Created(objectHotVideo.getString("date_created"));
                video.setDate_Modified(objectHotVideo.getString("date_modified"));
                video.setDate_Published(objectHotVideo.getString("date_published"));
                video.setUser_Created(objectHotVideo.getString("user_created"));
                video.setUser_Modified(objectHotVideo.getString("user_modified"));
                video.setConvert_Status(objectHotVideo.getString("convert_status"));
                video.setConvert_Time(objectHotVideo.getString("convert_time"));
                video.setYoutube_Url(objectHotVideo.getString("youtube_url"));
                video.setTags(objectHotVideo.getString("tags"));
                video.setDownload_Status(objectHotVideo.getInt("download_status"));
                video.setFb_Download(objectHotVideo.getString("fb_download"));
                video.setIcash(objectHotVideo.getString("icash"));
                video.setFb_Url(objectHotVideo.getString("fb_url"));
                video.setAws_Status(objectHotVideo.getString("aws_status"));
                video.setIcash_2(objectHotVideo.getString("icash_2"));
                video.setPrice_2(objectHotVideo.getDouble("price_2"));
                video.setPublisher_Category_ID(objectHotVideo.getString("publisher_category_id"));
                video.setView_Clip_Gold(objectHotVideo.getString("view_clip_gold"));
                video.setView_Clip_Icash(objectHotVideo.getString("download_clip_icash"));
                video.setDownload_Clip_Gold(objectHotVideo.getInt("download_clip_gold"));
                video.setDownload_Clip_Icash(objectHotVideo.getInt("download_clip_icash"));

                videos.add(video);
            }
        } catch (Exception json_ex) {
            json_ex.printStackTrace();
        } finally {
            this.cancel(true);
        }
    }
}
