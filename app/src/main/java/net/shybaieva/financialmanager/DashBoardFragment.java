package net.shybaieva.financialmanager;

import android.app.AlertDialog;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class DashBoardFragment extends Fragment {

    private FloatingActionButton fab_main, income_fab, expense_fab;

    private TextView fab_income_text, fab_expense_text;

    private boolean isFabOpen = false;

    private Animation openAnim, closeAnim;

    private FirebaseAuth firebaseAuth;
    private DatabaseReference incomeDataBase, expenseDataBase;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view =  inflater.inflate(R.layout.fragment_dashboard, container, false);

        firebaseAuth = FirebaseAuth.getInstance();
        FirebaseUser user = firebaseAuth.getCurrentUser();
        String userId = user.getUid();

        incomeDataBase = FirebaseDatabase.getInstance().getReference().child("IncomeData").child(userId);
        expenseDataBase = FirebaseDatabase.getInstance().getReference().child("ExpenseData").child(userId);

        fab_main = view.findViewById(R.id.main_fab);
        income_fab = view.findViewById(R.id.income_fab);
        expense_fab = view.findViewById(R.id.expense_fab);

        fab_income_text = view.findViewById(R.id.income_text);
        fab_expense_text = view.findViewById(R.id.expense_text);

        openAnim = AnimationUtils.loadAnimation(getActivity(), R.anim.fab_open);
        closeAnim = AnimationUtils.loadAnimation(getActivity(), R.anim.fab_close);

        fab_main.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                addData();

                if(isFabOpen){
                    income_fab.startAnimation(closeAnim);
                    income_fab.setClickable(false);

                    expense_fab.startAnimation(closeAnim);
                    expense_fab.setClickable(false);

                    fab_income_text.startAnimation(closeAnim);
                    fab_income_text.setClickable(false);

                    fab_expense_text.startAnimation(closeAnim);
                    fab_expense_text.setClickable(false);

                    isFabOpen = false;
                }
                else {
                    income_fab.startAnimation(openAnim);
                    income_fab.setClickable(true);

                    expense_fab.startAnimation(openAnim);
                    expense_fab.setClickable(true);

                    fab_income_text.startAnimation(openAnim);
                    fab_income_text.setClickable(true);

                    fab_expense_text.startAnimation(openAnim);
                    fab_expense_text.setClickable(true);

                    isFabOpen = true;
                }
            }
        });

        return view;
    }


    private void addData(){

        income_fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(getActivity());
                LayoutInflater inflater = LayoutInflater.from(getActivity());
                View myView = inflater.inflate(R.layout.insert_data_layout, null);
                dialogBuilder.setView(myView);
                final AlertDialog dialog = dialogBuilder.create();
                dialog.show();

                final EditText amount = myView.findViewById(R.id.amountEdit);
                final EditText type = myView.findViewById(R.id.typeEdit);
                final EditText note = myView.findViewById(R.id.noteEdit);

                Button save = myView.findViewById(R.id.saveBtn);
                Button cancel = myView.findViewById(R.id.cancelBtn);

                save.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String amountS = amount.getText().toString().trim();
                        String typeS = type.getText().toString().trim();
                        String noteS = note.getText().toString().trim();

                        if(TextUtils.isEmpty(amountS)){
                            amount.setError("");
                            Toast.makeText(getActivity(), "Amount is empty", Toast.LENGTH_SHORT).show();
                        }
                        else if(TextUtils.isEmpty(typeS)){
                            type.setError("");
                            Toast.makeText(getActivity(), "Type is empty", Toast.LENGTH_SHORT).show();
                        }

                        else{
                            Double doubleAmount = Double.parseDouble(amountS);

                        }

                    }
                });

                cancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                    }
                });

            }
        });




        expense_fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }



}
