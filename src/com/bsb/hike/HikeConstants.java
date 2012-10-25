package com.bsb.hike;

import com.bsb.hike.utils.AccountUtils;

public class HikeConstants
{
	public static final String APP_PUSH_ID = "768395314950";
	public static final String APP_FACEBOOK_ID = "425850510764995";
	public static final String APP_TWITTER_ID = "7LFaGIe5QXj05WN1YDDVaA";
	public static final String APP_TWITTER_SECRET = "LhgJVQ9eAmbb3EGdXpLD8B4RHf9SGPrzSqaOjuKL5o4";

	public static final String ANDROID = "android";

	public static final String MESSAGE = "msg";
	public static final String UI_TOPIC = "/u";
	public static final String APP_TOPIC = "/a";
	public static final String SERVICE_TOPIC = "/s";
	public static final String PUBLISH_TOPIC = "/p";

	public static final String TYPE = "t";
	public static final String DATA = "d";
	public static final String TO = "to";
	public static final String FROM = "f";
	public static final String SUB_TYPE = "st";

	public static final String HIKE_MESSAGE = "hm";
	public static final String SMS_MESSAGE = "sm";
	public static final String TIMESTAMP = "ts";
	public static final String MESSAGE_ID = "i";
	public static final String METADATA = "md";
	public static final String METADATA_DND = "dnd";
	public static final String ANALYTICS_EVENT = "ae";
	public static final String ALL_INVITEE = "ai";
	public static final String ALL_INVITEE_JOINED = "aij";
	public static final String ALL_INVITEE_2 = "all_invitee";
	public static final String ALL_INVITEE_JOINED_2 = "all_invitee_joined";
	public static final String NAME = "name";
	public static final String MSISDN = "msisdn";
	public static final String NEW_USER = "nu";
	public static final String EMAIL = "email";
	public static final String GENDER = "gender";
	public static final String VERSION = "v";
	public static final String CRITICAL = "c";
	public static final String INVITE_TOKEN = "invite_token";
	public static final String TOTAL_CREDITS_PER_MONTH = "tc";
	public static final String DND_NUMBERS = "dndnumbers";
	public static final String FILES = "files";
	public static final String CONTENT_TYPE = "ct";
	public static final String THUMBNAIL = "tn";
	public static final String FILE_NAME = "fn";
	public static final String FILE_KEY = "fk";
	public static final String CREDITS = "credits";
	public static final String ON_HIKE = "onhike";
	public static final String DND = "dnd";
	public static final String DND_USERS = "dndUsers";

	public static final String SOUND_PREF = "soundPref";
	public static final String VIBRATE_PREF = "vibratePref";
	public static final String SMS_PREF = "smsPref";
	public static final String HIKEBOT = "TD-HIKE";
	
	public static final String DONE = "Done";
	public static final String PIN_ERROR = "PinError";
	public static final String ADDRESS_BOOK_ERROR = "AddressBookError";
	public static final String CHANGE_NUMBER = "ChangeNumber"; 

	public static final String SEPARATOR = " - ";
	public static final String GROUP_PARTICIPANT_SEPARATOR = ", ";

	public static final String HELP_URL = "http://www.hike.in/help/android";
	public static final String T_AND_C_URL = "http://www.hike.in/terms";

	public static final String IMAGE = "Image";
	public static final String VIDEO = "Video";
	public static final String AUDIO = "Audio";

	public static final String IS_TYPING = "is typing...";

	public static final String FAVORITES = "Favorites";
	public static final String RECENT = "Recent";

	public static final String NEW_GROUP = "new_group";

	/* Constant used to name the preference file which saves the drafts*/
	public static final String DRAFT_SETTING = "draftSetting";

	/* how long to wait between sending publish and receiving an acknowledgement */
	public static final long MESSAGE_DELIVERY_TIMEOUT = 5*1000;

	/* how long to wait for a ping confirmation */
	public static final long PING_TIMEOUT = 5*1000;

	/* how long to wait to resend message. This should significantly greathr than PING_TIMEOUT */
	public static final long MESSAGE_RETRY_INTERVAL = 15*1000;

	/* quiet period of no changes before actually updating the db */
	public static final long CONTACT_UPDATE_TIMEOUT = 10*1000;

	/* how often to ping the server */
	public static final short KEEP_ALIVE = 2 * 60; /* 10 minutes */

	/* how often to ping after a failure */
	public static final int RECONNECT_TIME = 10; /* 10 seconds */

	/* the max amount (in seconds) the reconnect time can be */
	public static final int MAX_RECONNECT_TIME = 120;

	public static final int HIKE_SYSTEM_NOTIFICATION = 0;
	public static final String ADAPTER_NAME = "hikeadapter";
	
	/* constants for defining what to do after checking for updates*/
	public static final int NORMAL_UPDATE = 2;
	public static final int CRITICAL_UPDATE = 1;
	public static final int NO_UPDATE = 0;
	
	// More explanation required?
	public static final int NUM_TIMES_SCREEN_SHOULD_OPEN_BEFORE_TOOL_TIP = 2;
    public static final String APP_API_VERSION = "1";

	public static int NUM_SMS_PER_FRIEND = 10;
	public static int INITIAL_NUM_SMS = 100;

	public static int MAX_CHAR_IN_NAME = 20;

	public static int MAX_SMS_CONTACTS_IN_GROUP = 5;
	public static int MAX_CONTACTS_IN_GROUP = 10;

	public static int PROFILE_IMAGE_DIMENSIONS = 80;

	public static final String VALID_MSISDN_REGEX = "\\+?[0-9]{8,15}";

	public static final int MAX_BUFFER_SIZE_KB = 100;
	public static final int MAX_FILE_SIZE = 6*1024*1024;
	public static final int IMAGE_TRANSFER_CODE = 1188;
	public static final int VIDEO_TRANSFER_CODE = 1189;
	public static final int AUDIO_TRANSFER_CODE = 1190;
	public static final int RECORD_AUDIO_TRANSFER_CODE = 1191;
	public static final int MAX_DURATION_RECORDING_SEC = 120;

	public static final int MAX_DIMENSION_THUMBNAIL_PX = 180; 

	public static final int MAX_DIMENSION_FULL_SIZE_PX = 800;

	public static final int INITIAL_PROGRESS = 5;

	public static final int NO_CHANGE = 0;
	public static final int PARTICIPANT_STATUS_CHANGE = 1;
	public static final int NEW_PARTICIPANT = 2;

	public static final String MAIL = "support@hike.in";

	// Had to add this constant since its only available in the android API for Honeycomb and higher.
	public static final int FLAG_HARDWARE_ACCELERATED = 16777216;

	public static final int LOCAL_CLEAR_TYPING_TIME = 20 * 1000;

	// Number of recent contacts to show in the favorites drawer.
	public static final int RECENT_COUNT_IN_FAVORITE = 10;

	//Fiksu Currency
	public static final String CURRENCY = "INR";

	//Fiksu Usernames
	public static final String FACEBOOK = "facebook";
	public static final String TWITTER = "twitter";
	public static final String INVITE = "invite";
	public static final String FIRST_MESSAGE = "first_message";

	//Fiksu Prices
	public static final int FACEBOOK_CONNECT = 100;
	public static final int TWITTER_CONNECT = 100;
	public static final int INVITE_SENT = 50;
	public static final int FIRST_MSG_IN_DAY = 10;

	/*Maximum number of conversations to be made automatically when the user signs up*/
	public static final int MAX_CONVERSATIONS = 6;

	/*Constant used as a type to signify that this message was added locally by the client when the user signed up*/
	public static final String INTRO_MESSAGE = "im";

	public static final class Extras
	{
		public static final String MSISDN = "msisdn";
		public static final String ID = "id";
		public static final String NAME = "name";
		public static final String PREV_MSISDN = "prevMsisdn";
		public static final String PREV_ID = "prevId";
		public static final String PREV_NAME = "prevName";
		public static final String INVITE = "invite";
		public static final String MSG = "msg";
		public static final String PREF = "pref";
		public static final String EDIT = "edit";
		public static final String IMAGE_PATH ="image-path";
		public static final String SCALE = "scale";
		public static final String OUTPUT_X = "outputX";
		public static final String OUTPUT_Y = "outputY";
		public static final String ASPECT_X = "aspectX";
		public static final String ASPECT_Y = "aspectY";
		public static final String DATA = "data";
		public static final String RETURN_DATA = "return-data";
		public static final String BITMAP = "bitmap";
		public static final String CIRCLE_CROP = "circleCrop";
		public static final String SCALE_UP = "scaleUpIfNeeded";
		public static final String UPDATE_AVAILABLE = "updateAvailable";
		public static final String KEEP_MESSAGE = "keepMessage";
		public static final String SHOW_CREDITS_HELP = "showCreditsHelp";
		public static final String CREDITS_HELP_COUNTER = "CreditsHelpCounter";
		public static final String SIGNUP_TASK_RUNNING = "signupTaskRunning";
		public static final String SIGNUP_PART = "signupPart";
		public static final String SIGNUP_TEXT = "signupText";
		public static final String SIGNUP_ERROR = "signupError";
		public static final String TOOLTIP_SHOWING = "tooltipShowing";
		public static final String FADE_OUT = "fadeOut";
		public static final String FADE_IN = "fadeIn";
		public static final String ANIMATED_ONCE = "animatedOnce";
		public static final String EDIT_PROFILE = "editProfile";
		public static final String EMAIL = "email";
		public static final String GENDER = "gender";
		public static final String OVERLAY_SHOWING = "overlayShowing";
		public static final String GROUP_CHAT = "groupChat";
		public static final String EMOTICON_SHOWING = "emoticonShowing";
		public static final String EXISTING_GROUP_CHAT = "existingGroupChat";
		public static final String LEAVE_GROUP_CHAT = "leaveGroupChat";
		public static final String APP_STARTED_FIRST_TIME = "appStartedFirstTime";
		public static final String LATEST_VERSION = "latestVersion";
		public static final String SHOW_UPDATE_OVERLAY = "showUpdateOverlay";
		public static final String SHOW_UPDATE_TOOL_TIP = "showUpdateToolTip"; 
		public static final String UPDATE_TOOL_TIP_SHOWING = "updateToolTipShowing";
		public static final String UPDATE_MESSAGE = "updateMessage";
		public static final String URL_TO_LOAD = "urlToLoad";
		public static final String TITLE = "title";
		public static final String FIRST_TIME_USER = "firstTimeUser";
		public static final String IS_DELETING_ACCOUNT = "isDeletingAccount";
		public static final String SMS_MESSAGE = "incomingSMSMessage";
		public static final String GROUP_LEFT = "groupLeft";
		public static final String ALERT_CANCELLED = "alertCancelled";
		public static final String DEVICE_DETAILS_SENT = "deviceDetailsSent";
		public static final String SIGNUP_MSISDN_ERROR = "signupMsisdnError";
		public static final String FILE_TRANSFER_DIALOG_SHOWING = "fileTransferDialogShowing";
		public static final String FILE_PATH = "filePath";
		public static final String FILE_KEY = "fileKey";
		public static final String FILE_TYPE = "fileType";
		public static final String FILE_NAME = "fileName";
		public static final String RECORDER_DIALOG_SHOWING = "recorderDialogShowing";
		public static final String RECORDER_START_TIME = "recorderStartTime";
		public static final String IS_LEFT_DRAWER_VISIBLE = "isLeftDrawerVisible";
		public static final String IS_RIGHT_DRAWER_VISIBLE = "isRightDrawerVisible";
		public static final String FORWARD_MESSAGE = "forwardMessage";
		public static final String HELP_PAGE = "helpPage";
		public static final String WHICH_EMOTICON_CATEGORY = "whichEmoticonCategory";
		public static final String WHICH_EMOTICON_SUBCATEGORY = "whichEmoticonSubcategory";
		public static final String COUNTRY_CODE = "countryCode";
		public static final String GOING_BACK_TO_HOME = "goingBackToHome";
		public static final String UPDATE_URL = "updateURL";
		public static final String UPDATE_TO_IGNORE = "updateToIgnore";
		public static final String INTRO_MESSAGE_ADDED = "introMessageAdded";
	}

	public static final class LogEvent
	{
		// Common tags for Countly. Don't change.

	    public static final String TAG = "tag";
	    public static final String DEVICE_ID = "device_id";
	    public static final String OS = "_os";
	    public static final String OS_VERSION = "_os_version";
	    public static final String DEVICE = "_device";
	    public static final String RESOLUTION = "_resolution";
	    public static final String CARRIER = "_carrier";
	    public static final String APP_VERSION = "_app_version";

	    /*
	     *  Naming Convention - <screen><event><sub-event>
	     */
	    
	    /* 
	     * Home screen events
	     * <screen> = hoS
	     * <event> = profS, invS, feedS, delAC, delC, compB, addSC, creDtiPN, creDtiPY, upDtiPN, upDtipY, upDOBD, upDOB, smSY, smSN, groupS, dRB
	     */
	    public static final String MENU = "hoS";
	    public static final String DELETE_ALL_CONVERSATIONS_MENU = "hoSdelAC";
	    public static final String DELETE_CONVERSATION = "hoSdelC";
	    public static final String COMPOSE_BUTTON = "hoScompB";
	    public static final String ADD_SHORTCUT = "hoSaddSC";
	    public static final String HOME_TOOL_TIP_CLOSED = "hoScreDtiPN";
	    public static final String HOME_TOOL_TIP_CLICKED = "hoScreDtiPY";
	    public static final String HOME_UPDATE_TOOL_TIP_CLOSED = "hoSupDtiPN";
	    public static final String HOME_UPDATE_TOOL_TIP_CLICKED = "hoSupDtiPY";
	    public static final String HOME_UPDATE_OVERLAY_DISMISSED = "hoSupDOBD";
	    public static final String HOME_UDPATE_OVERLAY_BUTTON_CLICKED = "hoSupDOB";
	    public static final String DEFAULT_SMS_DIALOG_YES = "hoSsmSY";
	    public static final String DEFAULT_SMS_DIALOG_NO = "hoSsmSN";
	    public static final String DRAWER_BUTTON = "hoSdRB";

	    /* 
	     * Profile screen events
	     * <screen> = profS
	     * <event> = proES, credS, notyS, privS, helpS
	     */
	    public static final String EDIT_PROFILE = "profSproES";
	    public static final String NOTIFICATION_SCREEN = "profSnotyS";
	    public static final String PRIVACY_SCREEN = "profSprivS";

	    /* 
	     * Invite screen events
	     * <screen> = invS
	     * <event> = credB, creDtiPN, creDtiPY
	     */
	    public static final String INVITE_TOOL_TIP_CLOSED = "invScreDtiPN";
	    public static final String INVITE_TOOL_TIP_CLICKED = "invScreDtiPY";
	    public static final String CREDIT_TOP_BUTTON = "invScredB";
	    
	    /* 
	     * Chat thread screen events
	     * <screen> = chatS
	     * <event> = inVtiPN, inVtopB, blocK, forMsg, infoB, invOB, invOBD, opTiNtaP, calL, adD, grPinfO
	     */
	    public static final String CHAT_INVITE_TOOL_TIP_CLOSED = "chatSinVtiPN";
	    public static final String CHAT_INVITE_TOP_BUTTON = "chatSinVtopB";
	    public static final String MENU_BLOCK = "chatSblocK";
	    public static final String FORWARD_MSG = "chatSforMSG";
	    public static final String I_BUTTON = "chatSinfoB";
	    public static final String INVITE_OVERLAY_BUTTON = "chatSinvOB";
	    public static final String INVITE_OVERLAY_DISMISS = "chatSinvOBD";
	    public static final String OPT_IN_TAP_HERE = "chatSopTiNtaP";
	    public static final String MENU_CALL = "chatScalL";
	    public static final String MENU_ADD_TO_CONTACTS = "chatSadD";
	    public static final String GROUP_INFO_TOP_BUTTON = "chatSgrPinfO";
	    
	    /* 
	     * Credits screen events
	     * <screen> = credS
	     * <event> = inVB
	     */
	    public static final String INVITE_BUTTON_CLICKED = "credSinVB";

	    /*
	     * User Preferences
	     * <screen> = prefS
	     * <event> = smS
	     */
	    public static final String DEFAULT_SMS_CLIENT = "prefSsmS";

	    /*
	     * Group Info screen
	     * <screen> = groupS
	     * <event> = adDparT
	     */
		public static final String ADD_PARTICIPANT = "groupSadDparT";
	    
	    /*
	     * SignUp screen
	     * <screen> = signupS
	     * <event> = erroR
	     */
	    public static final String SIGNUP_ERROR = "signupSerroR";

	    /*
	     * Drawer screen
	     * <screen> = drS
	     * <event> = homE, gC, inV, reW, creD, proF, helP
	     */
	    public static final String DRAWER_HOME = "drShomE";
	    public static final String DRAWER_GROUP_CHAT = "drSgC";
	    public static final String DRAWER_INVITE = "drSinV";
	    public static final String DRAWER_REWARDS = "drSreW";
	    public static final String DRAWER_CREDITS = "drScreD";
	    public static final String DRAWER_PROFILE = "drSproF";
	    public static final String DRAWER_HELP = "drShelP";

	    /*
	     * Rewards screen
	     * <screen> = rewS
	     * <event> = inV, clM, faQ
	     */
	    public static final String REWARDS_INVITE = "rewSinV";
	    public static final String REWARDS_CLAIM = "rewSclM";
	    public static final String REWARDS_FAQ = "rewSfaQ";

	    /*
	     * Help screen
	     * <screen> = helpS
	     * <event> = conT, faQ
	     */
	    public static final String HELP_CONTACT = "helpSconT";
	    public static final String HELP_FAQ = "helpSfaQ";
	}

	public static final class MqttMessageTypes
	{
		public static final String MESSAGE_READ = "mr";
		public static final String MESSAGE = "m";
		public static final String SMS_CREDITS = "sc";
		public static final String DELIVERY_REPORT = "dr";
		public static final String USER_JOINED = "uj";
		public static final String USER_LEFT = "ul";
		public static final String START_TYPING = "st";
		public static final String END_TYPING = "et";
		public static final String INVITE = "i";
		public static final String ICON = "ic";
		public static final String INVITE_INFO = "ii";
		public static final String GROUP_CHAT_JOIN = "gcj";
		public static final String GROUP_CHAT_LEAVE = "gcl";
		public static final String GROUP_CHAT_END = "gce";
		public static final String GROUP_CHAT_NAME = "gcn";
		public static final String ANALYTICS_EVENT = "le";
		public static final String UPDATE_AVAILABLE = "ua";
		public static final String ACCOUNT_INFO = "ai";
		public static final String REQUEST_ACCOUNT_INFO = "rai";
		public static final String USER_OPT_IN = "uo";
		public static final String BLOCK_INTERNATIONAL_SMS = "bis";
	}
}
