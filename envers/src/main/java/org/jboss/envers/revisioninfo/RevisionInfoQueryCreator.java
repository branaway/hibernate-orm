/*
 * JBoss, Home of Professional Open Source
 *
 * Copyright 2008, Red Hat Middleware LLC, and others contributors as indicated
 * by the @authors tag. All rights reserved.
 *
 * See the copyright.txt in the distribution for a  full listing of individual
 * contributors. This copyrighted material is made available to anyone wishing
 * to use,  modify, copy, or redistribute it subject to the terms and
 * conditions of the GNU Lesser General Public License, v. 2.1.
 *
 * This program is distributed in the hope that it will be useful, but
 * WITHOUT A WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE.  See the GNU Lesser General Public License
 * for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License, v.2.1 along with this distribution; if not, write to the Free
 * Software Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA
 * 02110-1301, USA.
 *
 * Red Hat Author(s): Adam Warski
 */
package org.jboss.envers.revisioninfo;

import org.hibernate.Session;
import org.hibernate.Query;

import java.util.Date;

/**
 * @author Adam Warski (adam at warski dot org)
 */
public class RevisionInfoQueryCreator {
    private final String revisionDateQuery;
    private final String revisionNumberForDateQuery;
    private final String revisionQuery;

    public RevisionInfoQueryCreator(String revisionInfoEntityName, String revisionInfoIdName,
                                    String revisionInfoTimestampName) {
        revisionDateQuery = new StringBuilder()
                .append("select rev.").append(revisionInfoTimestampName)
                .append(" from ").append(revisionInfoEntityName)
                .append(" rev where ").append(revisionInfoIdName).append(" = :_revision_number")
                .toString();

        revisionNumberForDateQuery = new StringBuilder()
                .append("select max(rev.").append(revisionInfoIdName)
                .append(") from ").append(revisionInfoEntityName)
                .append(" rev where ").append(revisionInfoTimestampName).append(" <= :_revision_date")
                .toString();

        revisionQuery = new StringBuilder()
                .append("select rev from ").append(revisionInfoEntityName)
                .append(" rev where ").append(revisionInfoIdName)
                .append(" = :_revision_number")
                .toString();
    }

    public Query getRevisionDateQuery(Session session, Number revision) {
        return session.createQuery(revisionDateQuery).setParameter("_revision_number", revision);
    }

    public Query getRevisionNumberForDateQuery(Session session, Date date) {
        return session.createQuery(revisionNumberForDateQuery).setParameter("_revision_date", date.getTime());
    }

    public Query getRevisionQuery(Session session, Number revision) {
        return session.createQuery(revisionQuery).setParameter("_revision_number", revision);
    }
}
