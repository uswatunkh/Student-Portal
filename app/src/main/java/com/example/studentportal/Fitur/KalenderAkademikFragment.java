package com.example.studentportal.Fitur;

import android.Manifest;
import android.app.Activity;
import android.app.Dialog;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.core.app.ActivityCompat;
import androidx.core.content.FileProvider;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Environment;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.studentportal.BuildConfig;
import com.example.studentportal.DocumentPOJO;
import com.example.studentportal.Fragment.HomeFragment;
import com.example.studentportal.Fragment.SignupTabFragment;
import com.example.studentportal.R;
import com.example.studentportal.RetrofitClient;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.studentportal.app.AppController.TAG;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link KalenderAkademikFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class KalenderAkademikFragment extends Fragment {
    ImageView backKeterampilan;
    private EditText edtSn;
    private Button btnDownload, btnSave;

    private byte[] pdfInBytes;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public KalenderAkademikFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment KalenderAkademikFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static KalenderAkademikFragment newInstance(String param1, String param2) {
        KalenderAkademikFragment fragment = new KalenderAkademikFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.fragment_kalender_akademik, container, false);

        edtSn = root.findViewById(R.id.edtSN);
        btnDownload = root.findViewById(R.id.btnDownload);
        btnSave = root.findViewById(R.id.btnSaveFile);
        backKeterampilan= (ImageView) root.findViewById(R.id.backKeterampilan);
        backKeterampilan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openFragment(HomeFragment.newInstance("", ""));
            }
        });

        btnSave.setVisibility(View.GONE);

        btnDownload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                downloadDocument();
            }
        });

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (isStoragePermissionGranted()){
                    try {
                        save();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

            }
        });
        return root;
    }

    public void openFragment(Fragment fragment) {
        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.container, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    private void save() throws IOException {

        String root = Environment.getExternalStorageDirectory().toString();
        File myDir = new File(root + "/Download");

        if( !myDir.exists()){
            myDir.mkdir();
        }

        try {

            File fileLocation = new File(myDir, "Kalender_Akademik_PDF.pdf");

            FileOutputStream fos = new FileOutputStream(fileLocation);
            fos.write(pdfInBytes);
            fos.flush();
            fos.close();

            //Toast.makeText(getActivity(), "Saved", Toast.LENGTH_SHORT).show();
//            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
//            builder.setMessage("File Berhasil di Download")
//                    .setNegativeButton("Retry",null)
//                    .create()
//                    .show();
            ViewDialogSuccess alert = new ViewDialogSuccess();
            alert.showDialog(getActivity(), "File Berhasil di Download");


        }catch (Exception e){e.printStackTrace();}


    }

    public class ViewDialogSuccess {

        public void showDialog(Activity activity, String msg){
            final Dialog dialog = new Dialog(activity);
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            dialog.setCancelable(false);
            dialog.setContentView(R.layout.dialog_success);

            TextView text = (TextView) dialog.findViewById(R.id.text_dialog);
            text.setText(msg);

            Button dialogButton = (Button) dialog.findViewById(R.id.btn_dialog);
            dialogButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog.dismiss();
                }
            });

            dialog.show();

        }
    }

    public boolean isStoragePermissionGranted() {
        String TAG = "Storage Permission";
        if (Build.VERSION.SDK_INT >= 23) {
            if (getActivity().checkSelfPermission(android.Manifest.permission.WRITE_EXTERNAL_STORAGE)
                    == PackageManager.PERMISSION_GRANTED) {
                Log.v(TAG, "Permission is granted");
                return true;
            } else {
                Log.v(TAG, "Permission is revoked");
                ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
                return false;
            }
        }
        else { //permission is automatically granted on sdk<23 upon installation
            Log.v(TAG,"Permission is granted");
            return true;
        }
    }




    private void downloadDocument() {


        String sn = "14";

        Call<DocumentPOJO> call = RetrofitClient.getInstance().getAPI().downloadDocs(Integer.parseInt(sn));
        call.enqueue(new Callback<DocumentPOJO>() {
            @Override
            public void onResponse(Call<DocumentPOJO> call, Response<DocumentPOJO> response) {

                if(response.body() != null){

                    String encodedPdf = response.body().getEncodedPDF();
                    pdfInBytes = Base64.decode(encodedPdf, Base64.DEFAULT);

//                    btnSave.setVisibility(View.VISIBLE);
                    if (isStoragePermissionGranted()){
                        try {
                            save();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }

                    Toast.makeText(getActivity(), "File dapat disimpan ", Toast.LENGTH_SHORT).show();

                }else{
                    Toast.makeText(getActivity(), "Invalid SN", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<DocumentPOJO> call, Throwable t) {

            }
        });

    }

}