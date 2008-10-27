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
package org.jboss.envers.entities.mapper.relation.component;

import org.jboss.envers.entities.EntityInstantiator;
import org.jboss.envers.entities.mapper.relation.MiddleIdData;
import org.jboss.envers.tools.query.Parameters;

import java.util.Map;

/**
 * @author Adam Warski (adam at warski dot org)
 */
public final class MiddleRelatedComponentMapper implements MiddleComponentMapper {
    private final MiddleIdData relatedIdData;

    public MiddleRelatedComponentMapper(MiddleIdData relatedIdData) {
        this.relatedIdData = relatedIdData;
    }

    public Object mapToObjectFromFullMap(EntityInstantiator entityInstantiator, Map<String, Object> data,
                                         Object dataObject, Number revision) {
        return entityInstantiator.createInstanceFromVersionsEntity(relatedIdData.getEntityName(), data, revision);
    }

    public void mapToMapFromObject(Map<String, Object> data, Object obj) {
        relatedIdData.getPrefixedMapper().mapToMapFromEntity(data, obj);
    }

    public void addMiddleEqualToQuery(Parameters parameters, String prefix1, String prefix2) {
        relatedIdData.getPrefixedMapper().addIdsEqualToQuery(parameters, prefix1, prefix2);
    }
}
