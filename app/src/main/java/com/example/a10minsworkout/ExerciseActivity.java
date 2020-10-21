package com.example.a10minsworkout;

import androidx.appcompat.app.AppCompatActivity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.media.MediaPlayer;
import android.os.CountDownTimer;
import android.speech.tts.TextToSpeech;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.Gravity;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.os.Bundle;
import android.view.View;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.Objects;
import java.util.Random;
import pl.droidsonroids.gif.GifImageView;

public class ExerciseActivity extends AppCompatActivity {
    static Context context;
    Boolean ActivityRunning;
    Boolean RestTimerRunning=false;
    Boolean ExerciseTimerRunning=false;
    int resttime = 10;
    ArrayList<Integer> b;
    Boolean pause = false;
    Button yes;
    ImageView Pause;
    Long currenttime;
    CountDownTimer countDownTimer;
    Button no;
    RecyclerView recyclerView;
    ArrayList<Integer> a;
    TextView timer;
    TextView upcoming;
    ProgressBar progressBar;
    ProgressBar exerciseProgressBar;
    MediaPlayer song;
    Dialog custom;
    LinearLayout timerLinearLayout;
    LinearLayout ExerciseTimerLinearLayout;
    TextView exerciseNameTextView;
    TextView exerciseTimerTextView;
    Integer numberOfExercise;
    GifImageView ExerciseGif;
    List<String> arrayList;
    TextToSpeech T1;
    ExerciseProgressRecyclerView adapter;
    List<String> GifNamesArray;
    int i = 0;
    int Workoutlength = 30;


    public void ExerciseTimer(long lex) {
        ExerciseTimerRunning = true;

        upcoming.setVisibility(View.INVISIBLE);
        String uri = "@drawable/" + GifNamesArray.get(i);
        int imageResource = getResources().getIdentifier(uri, null, getPackageName());
        exerciseNameTextView.setText(arrayList.get(i));
        ExerciseGif.setImageResource(imageResource);
        exerciseProgressBar.setMax(Workoutlength);
        ExerciseTimerLinearLayout.setVisibility(View.VISIBLE);
        countDownTimer = new CountDownTimer((lex * 1000) + 500, 1000) {
            @Override
            public void onTick(long l) {
                exerciseTimerTextView.setText(String.valueOf((l / 1000)));
                exerciseProgressBar.setProgress(Integer.parseInt(String.valueOf(l / 1000)));
                if(custom!=null) {
                    if (!custom.isShowing()) {
                        ActivityRunning = true;
                    } else {

                    }
                }

                currenttime = l;
                if (l / 1000 == 3 && ActivityRunning) {
                    song = MediaPlayer.create(getApplicationContext(), R.raw.threesec);
                    song.start();
                } else if (l / 1000 == Workoutlength / 2) {
                    T1.speak("Half The Time!", TextToSpeech.QUEUE_FLUSH, null);
                }
            }

            @Override
            public void onFinish() {
                i++;
                if (i != numberOfExercise && ActivityRunning) {
                    b.set(i - 1, 0);
                    adapter.notifyDataSetChanged();
                    ExerciseTimerRunning = false;
                    RestTimer(resttime);
                } else if (i == numberOfExercise && ActivityRunning) {
                    Intent intent = new Intent(getApplicationContext(), FinishActivity.class);
                    intent.putExtra("WorkoutType", MainActivity.difficulty);
                    startActivity(intent);
                    finish();
                }
            }
        }.start();
    }


    public void RestTimer(long mainresttime) {
        String uri = "@drawable/" + "restt";
        exerciseProgressBar.setMax(resttime);
        if(mainresttime==(resttime)) {
            Random random = new Random();
            int G = random.nextInt(7);
            switch (G) {
                case 0:
                    T1.speak("Well Done!", TextToSpeech.QUEUE_FLUSH, null);
                    break;
                case 1:
                    T1.speak("Hang In There!", TextToSpeech.QUEUE_FLUSH, null);
                    break;
                case 2:
                    T1.speak("You Can Do It!", TextToSpeech.QUEUE_FLUSH, null);
                    break;
                case 3:
                    T1.speak("You Did Good!", TextToSpeech.QUEUE_FLUSH, null);
                    break;
                case 4:
                    T1.speak("Come On You Can Do This!", TextToSpeech.QUEUE_FLUSH, null);
                    break;
                case 5:
                    T1.speak("You Got This!", TextToSpeech.QUEUE_FLUSH, null);
                    break;
                case 6:
                    T1.speak("Piece Of Cake For You", TextToSpeech.QUEUE_FLUSH, null);
                    break;
            }
        }
        int imageResource = getResources().getIdentifier(uri, null, getPackageName());
        RestTimerRunning = true;
        ExerciseGif.setImageResource(imageResource);
        upcoming.setVisibility(View.VISIBLE);
        upcoming.setText("UPCOMING EXERCISE:-" + arrayList.get(i).toUpperCase());
        exerciseNameTextView.setText("Rest!!! Well Done!");
        countDownTimer = new CountDownTimer((mainresttime * 1000) + 500, 1000) {
            @Override
            public void onTick(long l) {

                exerciseTimerTextView.setText(String.valueOf((l / 1000)));
                exerciseProgressBar.setProgress(Integer.parseInt(String.valueOf(l / 1000)));
                if(custom!=null) {
                    if (!custom.isShowing()) {
                        ActivityRunning = true;
                    } else {

                    }
                }
                currenttime=l;
                if (l / 1000 == 3 && ActivityRunning) {
                    song = MediaPlayer.create(getApplicationContext(), R.raw.threesec);
                    song.start();
                } else if (l / 1000 == (resttime/2)) {
                    T1.speak("Upcoming Exercise " + arrayList.get(i) + "!", TextToSpeech.QUEUE_FLUSH, null);
                }
            }

            @Override
            public void onFinish() {
                RestTimerRunning = false;
                ExerciseTimer(Workoutlength);
            }
        }.start();

    }

    @Override
    public void onBackPressed() {
        if (ActivityRunning) {
            ActivityRunning = false;
            alertDialog();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise);


        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        Pause= findViewById(R.id.imageView4);
        ActivityRunning = true;
        yes = findViewById(R.id.yes);
        no = findViewById(R.id.no);
        context = getApplicationContext();
        T1 = new TextToSpeech(this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int i) {
                T1.setLanguage(Locale.ENGLISH);
                T1.speak("Get Ready!", TextToSpeech.QUEUE_FLUSH, null);
            }
        });
        if (MainActivity.difficulty.equals("Easy")) {
            Workoutlength=30;
            resttime=20;
            arrayList = Arrays.asList(getResources().getStringArray(R.array.easy));
            GifNamesArray = Arrays.asList(getResources().getStringArray(R.array.easygif));
        } else if (MainActivity.difficulty.equals("Medium")) {
            Workoutlength=30;
            resttime=15;
            arrayList = Arrays.asList(getResources().getStringArray(R.array.Medium));
            GifNamesArray = Arrays.asList(getResources().getStringArray(R.array.Mediumgif));
        } else if (MainActivity.difficulty.equals("Hard")) {
            Workoutlength=45;
            resttime=15;
            arrayList = Arrays.asList(getResources().getStringArray(R.array.Hard));
            GifNamesArray = Arrays.asList(getResources().getStringArray(R.array.Hardgif));
        } else {
            Workoutlength=45;
            resttime=15;
            arrayList = Arrays.asList(getResources().getStringArray(R.array.Crazy));
            GifNamesArray = Arrays.asList(getResources().getStringArray(R.array.Crazygif));
        }
        upcoming = findViewById(R.id.textView3);
        TextView firstupcoming = findViewById(R.id.textView4);
        firstupcoming.setText("UPCOMING EXERCISE:- " + arrayList.get(i));
        numberOfExercise = arrayList.size();
        Rv();
        Pause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pause();
            }
        });
        exerciseProgressBar = findViewById(R.id.Exerciseprogress);
        exerciseTimerTextView = findViewById(R.id.exercisetimerTextview);
        ExerciseGif = findViewById(R.id.ExerciseGif);
        exerciseNameTextView = findViewById(R.id.ExerciseName);
        timerLinearLayout = findViewById(R.id.LinearLayoutExercise);
        ExerciseTimerLinearLayout = findViewById(R.id.Exercise);
        progressBar = findViewById(R.id.progress);
        timer = findViewById(R.id.timerTextView);
        start(10500);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }

    @Override
    protected void onStop() {
        super.onStop();
        if(!pause) {
            pause();
        }
    }

//    @Override
//    protected void onPause() {
//        super.onPause();
//        pause();
//    }

    public void Rv() {
        recyclerView = findViewById(R.id.rvExercise);
        a = new ArrayList<>();
        b = new ArrayList<>();
        for (int j = 1; j <= numberOfExercise; j++) {
            a.add(j);
            b.add(1);
        }
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        setAdapter();
    }
    public void start(long l){
        countDownTimer = new CountDownTimer(l, 1000) {
            @Override
            public void onTick(long l) {
                timer.setText(String.valueOf((l / 980)));
                progressBar.setProgress(Integer.parseInt(String.valueOf(l / 1000)));
                if(custom!=null) {
                    if (!custom.isShowing()) {
                        ActivityRunning = true;
                    } else {

                    }
                }
                currenttime=l;
                if (l / 1000 == 3 && ActivityRunning) {
                    song = MediaPlayer.create(getApplicationContext(), R.raw.threesec);
                    song.start();
                } else if (l / 1000 == 5 && ActivityRunning) {
                    T1.speak(numberOfExercise + "Exercises To Do!", TextToSpeech.QUEUE_FLUSH, null);
                }
            }

            @Override
            public void onFinish() {
                timerLinearLayout.setVisibility(View.INVISIBLE);
                ExerciseTimer(Workoutlength);

            }
        }.start();
    }

    public void setAdapter() {
        adapter = new ExerciseProgressRecyclerView(a, b);
        recyclerView.setAdapter(adapter);
    }

    public void alertDialog() {

        pause=true;
        countDownTimer.cancel();
        custom = new Dialog(this);
        custom.setContentView(R.layout.dialog);
        custom.setCancelable(false);
        if(song!=null) {
            song.stop();
        }
        Window window = custom.getWindow();
        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
        window.setGravity(Gravity.CENTER);
        custom.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        custom.findViewById(R.id.yes).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ActivityRunning = false;
                finish();
                custom.dismiss();

            }
        });
        custom.findViewById(R.id.no).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pause = false;
                if (ExerciseTimerRunning) {
                    ExerciseTimer((currenttime / 1000) - 1);
                } else if(RestTimerRunning) {
                    RestTimer((currenttime / 1000) - 1);
                }else{
                    start(currenttime);
                }
                ActivityRunning = true;
                custom.dismiss();
            }
        });
        custom.show();
    }
    public void pause(){
        pause=true;
        ActivityRunning=false;
        countDownTimer.cancel();
        if(song!=null) {
            song.stop();
        }
        custom = new Dialog(this);
        custom.setContentView(R.layout.play);
        custom.setCancelable(false);
        Window window = custom.getWindow();
        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
        window.setGravity(Gravity.CENTER);
        custom.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        custom.findViewById(R.id.imageView6).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pause=false;
                if (ExerciseTimerRunning) {
                    ExerciseTimer((currenttime / 1000) - 1);
                } else if(RestTimerRunning) {
                    RestTimer((currenttime / 1000) - 1);
                }else{
                    start(currenttime-1000);
                }
                ActivityRunning = true;
                custom.dismiss();
            }
        });
        custom.show();

    }

}
