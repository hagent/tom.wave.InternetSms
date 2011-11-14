package tom.wave.InternetSms;

import java.io.IOException;

import org.jsoup.Jsoup;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class InternetSmsActivity extends Activity {
	TextView textView;
	TextView button;
    final int loadDialogId = 14;

	@Override
    public void onCreate(Bundle savedInstanceState) {
    	
    	super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        textView = (TextView) findViewById(R.id.editText);
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
        });
    }
    
	protected ProgressDialog onCreateDialog(int dialogId){
		if(dialogId == loadDialogId){
			ProgressDialog progress = new ProgressDialog(this);
			progress.setMessage("Loading...");
	        return progress;
		}
		
		return null;
	}
    
	private void onSaveButtonClicked() throws IOException {
		new PageLoader().execute("bash.org.ru");
	}
	
	public class PageLoader extends AsyncTask<String, Integer, String> {
		@Override
		protected String doInBackground(String... urls) {
//			Object doc = Jsoup.connect("http://en.wikipedia.org/").get();
//			Elements newsHeadlines = doc.select("#mp-itn b a");
			 publishProgress(1);
	        return "hee";
	    }
		
		@Override
	    protected void onProgressUpdate(Integer... progress) {
			showDialog(loadDialogId);
	    }
	    
	    @Override
	    protected void onPostExecute(String result) {
	    	dismissDialog(loadDialogId);
	    	textView.setText(result);
	    }
	}
}