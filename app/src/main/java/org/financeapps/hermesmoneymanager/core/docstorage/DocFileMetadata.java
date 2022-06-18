package org.financeapps.hermesmoneymanager.core.docstorage;

import org.financeapps.hermesmoneymanager.utils.MmxDate;

/**
 * Metadata for the file selected in the document storage, using Storage Access Framework.
 */
public class DocFileMetadata {
    public String Uri;
    public String Name;
    public int Size;
    public MmxDate lastModified;
}
