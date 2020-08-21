package net.shybaieva.financialmanager;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class DashBoardFragment extends Fragment {

    private FloatingActionButton fab_main, income_fab, expense_fab;

    private TextView fab_income_text, fab_expense_text;

    private boolean isFabOpen = false;

    private Animation openAnim, closeAnim;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view =  inflater.inflate(R.layout.fragment_dashboard, container, false);

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

}
