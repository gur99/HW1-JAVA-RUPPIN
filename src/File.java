
public class File {
	private String FileName;
	private String FileType;

	public File(String FileName, String FileType) {
		setFileName(FileName);
		setFileType(FileType);
	}

	public void setFileName(String FileName) throws IllegalArgumentException {
		if (FileName == null || FileName.isEmpty()) {
			throw new IllegalArgumentException("FileName cannot be null or empty");
		}
		this.FileName = FileName;
	}

	public String getFileName() {
		return FileName;
	}

	public void setFileType(String FileType) {
		if (FileType == null || FileType.isEmpty()) {
			throw new IllegalArgumentException("FileType cannot be null or empty");
		}
		this.FileType = FileType;
	}

	public String getFileType() {
		return FileType;
	}

	@Override
	public String toString() {
		return "<File Name: " + getFileName() + "File Type: " + getFileType() + ">";
	}

}
