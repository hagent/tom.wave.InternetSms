package tom.wave.InternetSms;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
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
					onSaveButtonClicked();
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
    
	private void onSaveButtonClicked() {
		new PageLoader().execute("http://bash.org.ru/");
	}
	
	public class PageLoader extends AsyncTask<String, Integer, String> {
		@Override
		protected String doInBackground(String... urls) {
			 publishProgress(1);

			if(urls.length > 0){
				try {
					Document doc = Jsoup.connect(urls[0]).get();
			        return doc.html();

				} catch (IOException e) {
					e.printStackTrace();
					Log.e("TOMEXCEPTION", "uncatched IOException");
					return "uncathed IOException";
				} catch (Exception e) {
					e.printStackTrace();
					Log.e("TOMEXCEPTION", "uncatched Exception");
					return "uncathed Exception";
				}
			}
			
			return "";
			
//			Elements newsHeadlines = doc.select("#mp-itn b a");
			
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