package ua.goit.java.enums;

public enum VolumeData {
    VOLUME_10K(10000),
    VOLUME_100K(100000),
    VOLUME_1000K(1000000);

    private int volumeData;

    VolumeData(int volumeData) {
        this.volumeData = volumeData;
    }

    public int getVolumeData() {
        return volumeData;
    }
}
