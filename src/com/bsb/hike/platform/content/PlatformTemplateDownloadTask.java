package com.bsb.hike.platform.content;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Observable;
import java.util.Observer;

import com.bsb.hike.platform.content.PlatformContent.ErrorCode;

import android.os.AsyncTask;

/**
 * Download and store template
 * 
 * @author Atul M
 * 
 */
class PlatformTemplateDownloadTask extends AsyncTask<Void, Void, Void>
{
	private PlatformContentRequest mRequest;

	/**
	 * Instantiates a new platform template download task.
	 * 
	 * @param content
	 *            the content
	 * @param listener
	 *            the listener
	 */
	public PlatformTemplateDownloadTask(PlatformContentRequest argRequest)
	{
		// Get ID from content and call http
		mRequest = argRequest;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.os.AsyncTask#doInBackground(Params[])
	 */
	@Override
	protected Void doInBackground(Void... params)
	{

//		if (PlatformContentCache.getTemplate(mRequest) != null)
//		{
//			PlatformRequestManager.setReadyState(mRequest);
//			return null;
//		}
		
		// Fetch S3 URL
		String templateDownloadUrl = mRequest.getContentData().getLayout_url();

		// Create temp folder
		File tempFolder = new File(PlatformContentConstants.PLATFORM_CONTENT_DIR + PlatformContentConstants.TEMP_DIR_NAME);
		tempFolder.mkdirs();

		File zipFile = new File(PlatformContentConstants.PLATFORM_CONTENT_DIR + PlatformContentConstants.TEMP_DIR_NAME, mRequest.getContentData().getId() + ".zip");

		// Start downloading ZIP to temporary folder
		InputStream input = null;
		OutputStream output = null;
		HttpURLConnection connection = null;
		boolean isDownloaded = false;

		try
		{
			URL url = new URL(templateDownloadUrl);
			connection = (HttpURLConnection) url.openConnection();
			connection.connect();

			if (connection.getResponseCode() != HttpURLConnection.HTTP_OK)
			{
				throw new IOException("Server returned HTTP " + connection.getResponseCode() + " " + connection.getResponseMessage());
			}
		}
		catch (IOException ioe)
		{
			ioe.printStackTrace();
			PlatformRequestManager.reportFailure(mRequest, ErrorCode.LOW_CONNECTIVITY);
		}

		try
		{
			// download the file
			input = connection.getInputStream();

			output = new FileOutputStream(zipFile);

			byte data[] = new byte[4096];

			int count;
			while ((count = input.read(data)) != -1)
			{
				output.write(data, 0, count);
			}
			isDownloaded = true;
		}
		catch (IOException ioe)
		{
			ioe.printStackTrace();
			PlatformRequestManager.reportFailure(mRequest, ErrorCode.STORAGE_FULL);
		}
		catch (IllegalStateException ise)
		{
			ise.printStackTrace();
			PlatformRequestManager.reportFailure(mRequest, ErrorCode.UNKNOWN);
		}
		finally
		{
			try
			{
				if (output != null)
					output.close();

				if (input != null)
					input.close();

				if (connection != null)
					connection.disconnect();
			}
			catch (IOException ignored)
			{
				// Do nothing
			}
		}

		if (isDownloaded)
		{
			// unzip
			unzipWebFile(zipFile.getAbsolutePath(), PlatformContentConstants.PLATFORM_CONTENT_DIR, new Observer()
			{
				@Override
				public void update(Observable observable, Object data)
				{
					// delete temp folder
					File tempFolder = new File(PlatformContentConstants.PLATFORM_CONTENT_DIR + PlatformContentConstants.TEMP_DIR_NAME);
					PlatformContentUtils.deleteDirectory(tempFolder);
					PlatformRequestManager.setReadyState(mRequest);
				}
			});
		}
		else
		{
			// Could not download template files due to come reason. TODO We can implement retry here. Leaving for v1.
			PlatformRequestManager.remove(mRequest);
		}

		return null;
	}

	private void unzipWebFile(String zipFilePath, String unzipLocation, Observer observer)
	{
		HikeUnzipTask unzipper = new HikeUnzipTask(zipFilePath, unzipLocation);
		unzipper.addObserver(observer);
		unzipper.unzip();
	}
}