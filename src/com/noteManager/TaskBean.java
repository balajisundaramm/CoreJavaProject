package com.noteManager;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Balaji on 28/11/17.
 */
public class TaskBean implements Serializable {
    private String name,description,dueDate,tagLine,status,cre_Date;
    private int priority;

    public TaskBean() {

    }

    public TaskBean(String name) {
        this.name = name;
    }

    public TaskBean(String name, String description, String dueDate, String tagLine,
                    String status, String cre_Date, int priority) {
        this.name = name;
        this.description = description;
        this.dueDate = dueDate;
        this.tagLine = tagLine;
        this.status = status;
        this.cre_Date = cre_Date;
        this.priority = priority;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
        //System.out.println("Bean-->setName"+name);
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
        //System.out.println("Bean-->setDes"+description);

    }

    public String getDueDate() {
        return dueDate;
    }

    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
       // System.out.println("Bean-->setDueDate"+dueDate);
    }

    public String getTagLine() {
        return tagLine;
    }

    public void setTagLine(String tag) {
        this.tagLine = tag;
       // System.out.println("Bean--> setTagLine"+tag);
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
       // System.out.println("Bean--> setPriority"+priority);
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCre_Date() {
        return cre_Date;
    }

    public void setCre_Date(String cre_Date) {
        this.cre_Date = cre_Date;
    }

    @Override
    public String toString() {
        return "TaskBean{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", dueDate='" + dueDate + '\'' +
                ", tagLine='" + tagLine + '\'' +
                ", status='" + status + '\'' +
                ", cre_Date='" + cre_Date + '\'' +
                ", priority=" + priority +
                '}';
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((cre_Date == null) ? 0 : cre_Date.hashCode());
        result = prime * result + ((description == null) ? 0 : description.hashCode());
        result = prime * result + ((dueDate == null) ? 0 : dueDate.hashCode());
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        result = prime * result + priority;
        result = prime * result + ((status == null) ? 0 : status.hashCode());
        result = prime * result + ((tagLine == null) ? 0 : tagLine.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof TaskBean)) {
            return false;
        }
        TaskBean other = (TaskBean) obj;
        if (cre_Date == null) {
            if (other.cre_Date != null) {
                return false;
            }
        } else if (!cre_Date.equals(other.cre_Date)) {
            return false;
        }
        if (description == null) {
            if (other.description != null) {
                return false;
            }
        } else if (!description.equals(other.description)) {
            return false;
        }
        if (dueDate == null) {
            if (other.dueDate != null) {
                return false;
            }
        } else if (!dueDate.equals(other.dueDate)) {
            return false;
        }
        if (name == null) {
            if (other.name != null) {
                return false;
            }
        } else if (!name.equals(other.name)) {
            return false;
        }
        if (priority != other.priority) {
            return false;
        }
        if (status == null) {
            if (other.status != null) {
                return false;
            }
        } else if (!status.equals(other.status)) {
            return false;
        }
        if (tagLine == null) {
            if (other.tagLine != null) {
                return false;
            }
        } else if (!tagLine.equals(other.tagLine)) {
            return false;
        }
        return true;
    }


}
