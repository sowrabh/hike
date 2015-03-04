package com.bsb.hike.photos.views;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;

import com.bsb.hike.HikeConstants;
import com.bsb.hike.photos.HikePhotosUtils;


/**
 * @author akhiltripathi
 *	
 * Custom View for the doodle thumbnails
 * 
 */
public class DoodleEffectItemLinearLayout extends EffectItemLinearLayout
{
	private int brushWidth;

	private int brushColor;

	private int ringColor;

	public DoodleEffectItemLinearLayout(Context context, AttributeSet attrs)
	{
		super(context, attrs);
		// TODO Auto-generated constructor stub
		brushWidth = HikePhotosUtils.dpToPx(context, HikeConstants.HikePhotos.PREVIEW_BRUSH_WIDTH);
		brushColor = HikeConstants.HikePhotos.DEFAULT_BRUSH_COLOR;
		ringColor = HikeConstants.HikePhotos.DEFAULT_RING_COLOR;
		setImage(getCircleIcon());
	}

	public void refresh()
	{

		setImage(getCircleIcon());
		invalidate();
	}

	public int getBrushColor()
	{
		return brushColor;
	}

	public void setBrushColor(int Color)
	{
		this.brushColor = Color;
	}

	public int getRingColor()
	{
		return ringColor;
	}

	public void setRingColor(int Color)
	{
		this.ringColor = Color;
	}

	private Bitmap getCircleIcon()
	{
		int diameter = brushWidth + HikePhotosUtils.dpToPx(this.getContext(), 4);
		Bitmap bitmap = Bitmap.createBitmap(diameter, diameter, Config.ARGB_8888);
		Paint paint = new Paint();
		paint.setAntiAlias(true);
		paint.setColor(ringColor);
		Canvas canvas = new Canvas(bitmap);
		canvas.drawCircle(diameter / 2, diameter / 2, (diameter / 2), paint);
		paint.setColor(brushColor);
		canvas.drawCircle(diameter / 2, diameter / 2, (brushWidth / 2), paint);
		return bitmap;
	}

	public void setBrushWidth(int brushWidth)
	{
		this.brushWidth = brushWidth;
	}

	public int getBrushWidth()
	{
		return brushWidth;
	}

}