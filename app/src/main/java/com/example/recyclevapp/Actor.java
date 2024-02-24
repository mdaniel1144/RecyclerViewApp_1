package com.example.recyclevapp;

public class Actor {

    private String m_Name;
    private String m_Description;
    private int m_ImageSrc;
    private int m_ID;

    public Actor(String i_Name, String i_Description, int i_Image, int i_ID) {
        this.m_Name = i_Name;
        this.m_Description = i_Description;
        this.m_ImageSrc = i_Image;
        this.m_ID = i_ID;
    }

    public void setM_Name(String m_Name)
    {
        this.m_Name = m_Name;
    }

    public void setM_Description(String i_Description)
    {
        this.m_Description = i_Description;
    }

    public void setImage(int i_Image)
    {
        this.m_ImageSrc = i_Image;
    }

    public String getM_Name()
    {
        return m_Name;
    }

    public String getM_Description()
    {
        return m_Description;
    }

    public int getM_ID()
    {
        return m_ID;
    }

    public int getImage()
    {
        return m_ImageSrc;
    }
}








