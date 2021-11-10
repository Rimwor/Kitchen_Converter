package fr.afpa.kitchen_converter;

import android.Manifest;
import android.app.DownloadManager;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import fr.afpa.kitchen_converter.model.UnitManager;

public class TableActivity extends AppCompatActivity {

    // DECLARATIONS of the variables ================================= //
    private final static String TAG = "TableActivity";                   // TAG : onActivityCheckupSpy

    private DownloadManager downloadManager;
    private String fileName=null;
    private long downLoadId;

    // onCREATE ====================================================== //
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.table);
        initializeDownloadManager();

        Intent iTable = getIntent();

        // DATE BASE
        UnitManager.getAll(this); // test if DB works
    }

    // FUNCTIONS ===================================================== //

    /**
     * Initialize download manager
     */
    private void initializeDownloadManager() {
        downloadManager= (DownloadManager) getSystemService(DOWNLOAD_SERVICE);
        fileName="Kitchen_Converter_Table.jpg";
    }

    /**
     * Set the title and desc of this download, to be displayed in notifications
     */
    private void downloadFile(){
        DownloadManager.Request request=new DownloadManager.Request(Uri.parse("https://i.pinimg.com/originals/b0/25/be/b025be365c934c8ae4eda1fee4f82153.jpg"));
        request.setTitle("Kitchen_Converter_Table")
                .setDescription("File is downloading...")
                .setDestinationInExternalFilesDir(this,
                        Environment.DIRECTORY_DOWNLOADS,fileName)
                .setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
        // Enqueue the download.The download will start automatically once the download manager is ready
        // to execute it and connectivity is available.
        downLoadId=downloadManager.enqueue(request);
    }

    /**
     * Handle button clicks : DOWNLOAD button
     * @param view
     */
    public void clickView(View view){
        switch (view.getId()){
            case R.id.download:
                downloadFile();
                Toast.makeText(getBaseContext(), R.string.msg_downloading, Toast.LENGTH_SHORT).show();
                break;
        }
    }

    /**
     * BACK button
     * @param view
     */
    public void back(View view) {
        Log.i(TAG, "back");
        TableActivity.this.finish();
    }

}
