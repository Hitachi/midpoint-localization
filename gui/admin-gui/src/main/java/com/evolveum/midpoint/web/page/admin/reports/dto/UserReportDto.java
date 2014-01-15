/*
 * Copyright (c) 2010-2013 Evolveum
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.evolveum.midpoint.web.page.admin.reports.dto;

import com.evolveum.midpoint.xml.ns._public.common.common_2a.ExportType;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

/**
 *  @author shood
 * */
public class UserReportDto implements Serializable{

    public static final String F_FROM = "from";
    public static final String F_TO = "to";
    public static final String F_DESCRIPTION = "description";
    public static final String F_EXPORT_TYPE = "exportType";

    private Date from;
    private Date to;
    private ExportType exportType;
    private String description;

    public Date getFrom() {
        if (from == null) {
            from = new Date();
        }
        return from;
    }

    public void setFrom(Date from) {
        this.from = from;
    }

    public Date getTo() {
        if (to == null) {
            to = new Date();
        }
        return to;
    }

    public void setTo(Date to) {
        this.to = to;
    }

    public ExportType getExport() {
        return exportType;
    }

    public void setExport(ExportType export) {
        this.exportType = export;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Timestamp getDateFrom() {
        return new Timestamp(getFrom().getTime());
    }

    public Timestamp getDateTo() {
        return new Timestamp(getTo().getTime());
    }
}
