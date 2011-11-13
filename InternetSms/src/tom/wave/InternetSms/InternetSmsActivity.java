package tom.wave.InternetSms;

import java.io.IOException;

import org.jsoup.Jsoup;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class InternetSmsActivity extends Activity {
	TextView textView;
	TextView button;
	
	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        textView = (TextView) findViewById(R.id.label);
        button = (TextView) findViewById(R.id.button1);
        
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                try {
					onSaveButtonClicked();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
            }

			private void onSaveButtonClicked() throws IOException {
				textView.setText("gggg");
				Object doc = Jsoup.connect("http://en.wikipedia.org/").get();
//				Elements newsHeadlines = doc.select("#mp-itn b a");
				textView.setText(doc.getClass().getName());
			}
        });
    }
}