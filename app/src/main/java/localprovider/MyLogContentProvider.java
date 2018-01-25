package localprovider;

import android.net.Uri;

import no.nordicsemi.android.log.localprovider.LocalLogContentProvider;


public class MyLogContentProvider extends LocalLogContentProvider {
	/** The authority for the contacts provider. */
	public static final String AUTHORITY = "no.nordicsemi.android.log.example.tian";
	/** A content:// style uri to the authority for the log provider. */
	public static final Uri AUTHORITY_URI = Uri.parse("content://" + AUTHORITY);

	@Override
	protected Uri getAuthorityUri() {
		return AUTHORITY_URI;
	}

}
