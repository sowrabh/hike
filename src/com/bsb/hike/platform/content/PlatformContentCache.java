package com.bsb.hike.platform.content;

import java.io.File;
import java.io.FilenameFilter;

import com.bsb.hike.analytics.AnalyticsConstants;
import com.bsb.hike.platform.HikePlatformConstants;
import com.bsb.hike.utils.HikeAnalyticsEvent;
import org.json.JSONException;
import org.json.JSONObject;

import android.support.v4.util.LruCache;
import android.text.TextUtils;
import android.util.Log;

import com.samskivert.mustache.Template;

/**
 * Responsible for maintaining cache for formed data and pre-compiled templates.
 */
class PlatformContentCache
{

	private static String TAG = "PlatformContentCache";

	private static int formedContentCacheSize = 4 * 1024 * 1024; // 4MB

	private static int templateCacheSize = 6; // Number of templates

	private static LruCache<Integer, PlatformContentModel> formedContentCache = new LruCache<Integer, PlatformContentModel>(formedContentCacheSize)
	{
		protected int sizeOf(Integer key, PlatformContentModel value)
		{
			return value.toString().getBytes().length;
		};
	};

	private static LruCache<Integer, Template> templateCache = new LruCache<Integer, Template>(templateCacheSize)
	{
		protected int sizeOf(Integer key, Template value)
		{
			return 1;
		};
	};

	/**
	 * Instantiates a new platform content cache.
	 */
	private PlatformContentCache()
	{

	}

	/**
	 * Gets the template.
	 * 
	 * @param content
	 *            the content
	 * @return the template
	 */
	public static Template getTemplate(PlatformContentRequest content)
	{
		Template template = templateCache.get(content.getContentData().templateHashCode());

		Log.d(TAG, "getting template - " + content.getContentData().getContentJSON());

		if (template == null)
		{
			template = loadTemplateFromDisk(content);
		}

		return template;
	}

	/**
	 * Put template.
	 * 
	 * @param hashCode
	 *            the hash code
	 * @param template
	 *            the template
	 */
	public static void putTemplate(int hashCode, Template template)
	{
		Log.d(TAG, "putting template in cache");
		if (template != null)
		{
			templateCache.put(hashCode, template);
		}
	}

	/**
	 * Load template from disk.
	 * 
	 * @param content
	 *            the content
	 * @return the template or null if the template is not found on disk
	 */
	private static Template loadTemplateFromDisk(PlatformContentRequest content)
	{
		Log.d(TAG, "loading template from disk");

		// if (verifyVersion(content))
		// {
		// // Continue loading
		// }
		// else
		// {
		// return null;
		// }

		File file = new File(PlatformContentConstants.PLATFORM_CONTENT_DIR + content.getContentData().getId(), content.getContentData().getTag());

		String templateString = PlatformContentUtils.readDataFromFile(file);

		if (TextUtils.isEmpty(templateString))
		{
			return null;
		}

		Log.d(TAG, "loading template from disk - complete");

		Template downloadedTemplate = PlatformTemplateEngine.compileTemplate(templateString);

		if (downloadedTemplate == null)
		{
			PlatformRequestManager.reportFailure(content, PlatformContent.ErrorCode.INVALID_DATA);
			PlatformRequestManager.remove(content);
		}
		else
		{
			templateCache.put(content.getContentData().templateHashCode(), downloadedTemplate);
		}

		return downloadedTemplate;
	}

	public static boolean verifyVersion(PlatformContentRequest content)
	{
		File file = new File(PlatformContentConstants.PLATFORM_CONTENT_DIR, content.getContentData().getId());

		if (file.exists() && file.isDirectory())
		{
			String[] fileList = file.list(new FilenameFilter()
			{
				@Override 
				public boolean accept(File dir, String filename)
				{
					if (filename.equals(PlatformContentConstants.PLATFORM_CONFIG_FILE_NAME))
					{
						return true;
					}
					else
					{
						return false;
					}
				}
			});

			if (fileList.length == 0)
			{
				// If config.json is not present we go ahead and use current version
				return true;
			}
			else
			{
				File configFile = new File(file.getAbsolutePath() + File.separator + fileList[0]);

				String configFileData = PlatformContentUtils.readDataFromFile(configFile);

				if (TextUtils.isEmpty(configFileData))
				{
					return true;
				}
				else
				{
					try
					{
						JSONObject configJson = new JSONObject(configFileData);
						String version = configJson.getString(PlatformContentConstants.PLATFORM_CONFIG_VERSION_ID);
						if (version.equals(content.getContentData().getVersion()))
						{
							return true;
						}
						else
						{
							return false;
						}
					}
					catch (JSONException e)
					{
						e.printStackTrace();
						return true;
					}
				}
			}
		}

		return false;
	}

	/**
	 * Put hot content.
	 * 
	 * @param content
	 *            the content
	 */
	public static void putFormedContent(PlatformContentModel content)
	{
		Log.d(TAG, "put formed content in cache");
		cardLoadAnalytics(content);

		formedContentCache.put(content.hashCode(), content);
	}

	private static void cardLoadAnalytics(PlatformContentModel content)
	{
		JSONObject platformJSON = new JSONObject();

		try
		{
			String state = content.cardObj.getLayoutId();
			state = state.substring(0,state.length() - 5);
			platformJSON.put(AnalyticsConstants.EVENT_KEY, HikePlatformConstants.CARD_LOADED);
			platformJSON.put(HikePlatformConstants.CARD_STATE, state);
			HikeAnalyticsEvent.analyticsForBots(AnalyticsConstants.UI_EVENT, AnalyticsConstants.VIEW_EVENT, platformJSON);
		}
		catch (JSONException e)
		{
			e.printStackTrace();
		}
		catch (NullPointerException npe)
		{
			npe.printStackTrace();
		}
		catch (IndexOutOfBoundsException ie)
		{
			ie.printStackTrace();
		}
	}

	/**
	 * Gets the formed content.
	 * 
	 * @param content
	 *            the content
	 * @return the hot content
	 */
	public static PlatformContentModel getFormedContent(PlatformContentRequest request)
	{
		Log.d(TAG, "get formed content from cache");

		return formedContentCache.get(request.getContentData().hashCode());
	}
}
