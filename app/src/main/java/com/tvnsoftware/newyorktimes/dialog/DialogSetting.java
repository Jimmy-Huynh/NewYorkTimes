package com.tvnsoftware.newyorktimes.dialog;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;

import com.tvnsoftware.newyorktimes.R;
import com.tvnsoftware.newyorktimes.adapter.CustomSpinerAdapter;
import com.tvnsoftware.newyorktimes.model.SetingModel;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * Created by Thieusike on 6/27/2017.
 */

public class DialogSetting {

    public interface Listener {
        public void onButtonClick(SetingModel setingModel);
    }

    private Dialog dialog;
    private Context mContext;
    private Listener mListener;
    private EditText mEdtDate;
    private Spinner mSpinSort;
    private SetingModel model = new SetingModel();
    private CheckBox mChkArts, mChkFashion, mChkSport;

    public DialogSetting(Context context, Listener listener) {
        mContext = context;
        mListener = listener;
    }

    public void showDialog() {

        dialog = new Dialog(mContext);

        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);

        dialog.setContentView(R.layout.setting_layout);

        mChkArts = (CheckBox) dialog.findViewById(R.id.chk_arts);
        mChkFashion = (CheckBox) dialog.findViewById(R.id.chk_fashion);
        mChkSport = (CheckBox) dialog.findViewById(R.id.chk_sports);

        Button btnSave = (Button) dialog.findViewById(R.id.btn_save);
        mEdtDate = (EditText) dialog.findViewById(R.id.edt_begin_date);
        mEdtDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDatePicker();
            }
        });
        mSpinSort = (Spinner) dialog.findViewById(R.id.spn_sort);
        String[] sorts = {"newest", "oldest"};
        CustomSpinerAdapter customSpinerAdapter = new CustomSpinerAdapter(mContext, sorts);
        mSpinSort.setAdapter(customSpinerAdapter);
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                model.setBeginDate(mEdtDate.getText().toString());
                model.setSort(mSpinSort.getSelectedItem().toString());
                model.setArts(mChkArts.isChecked());
                model.setFashion(mChkFashion.isChecked());
                model.setSports(mChkSport.isChecked());

                dialog.dismiss();
                mListener.onButtonClick(model);
            }
        });

        dialog.show();
    }

    private void openDatePicker() {

        Calendar newCalendar = Calendar.getInstance();
        newCalendar.set(newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));
        DatePickerDialog datePicker = new DatePickerDialog(mContext, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int monthOfYear, int dayOfMonth) {
                loadDate(year, monthOfYear, dayOfMonth);
            }
        }, newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));
        datePicker.getDatePicker().setMaxDate(System.currentTimeMillis());
        datePicker.show();
    }

    private void loadDate(int year, int monthOfYear, int dayOfMonth) {
        Calendar newDate = Calendar.getInstance();
        newDate.set(year, monthOfYear, dayOfMonth);
        SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyymmdd");
        mEdtDate.setText(dateFormatter.format(newDate.getTime()));
    }
}
