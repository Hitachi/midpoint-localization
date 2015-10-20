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

//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.4 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2012.02.24 at 02:46:19 PM CET 
//


package com.evolveum.midpoint.prism.foo;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;
import javax.xml.namespace.QName;

import com.evolveum.midpoint.prism.Containerable;
import com.evolveum.midpoint.prism.PrismContainerValue;


/**
 * <p>Java class for AssignmentType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="AssignmentType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="description" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="accountConstruction" type="{http://midpoint.evolveum.com/xml/ns/test/foo-1.xsd}AccountConstructionType" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute name="id" type="{http://www.w3.org/2001/XMLSchema}string" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "AssignmentType", propOrder = {
    "description",
    "accountConstruction"
})
public class AssignmentType
    implements Serializable, Containerable
{

	// This is NOT GENERATED. It is supplied here manually for the testing.
	public final static QName F_DESCRIPTION = new QName(ObjectType.NS_FOO, "description");
	public final static QName F_ACCOUNT_CONSTRUCTION = new QName(ObjectType.NS_FOO, "accountConstruction");
	public final static QName F_ACTIVATION = new QName(ObjectType.NS_FOO, "activation");
	
	private final static long serialVersionUID = 201202081233L;
    protected String description;
    protected AccountConstructionType accountConstruction;
    @XmlAttribute(name = "id")
    protected String id;

    /**
     * Gets the value of the description property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the value of the description property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDescription(String value) {
        this.description = value;
    }

    /**
     * Gets the value of the accountConstruction property.
     * 
     * @return
     *     possible object is
     *     {@link AccountConstructionType }
     *     
     */
    public AccountConstructionType getAccountConstruction() {
        return accountConstruction;
    }

    /**
     * Sets the value of the accountConstruction property.
     * 
     * @param value
     *     allowed object is
     *     {@link AccountConstructionType }
     *     
     */
    public void setAccountConstruction(AccountConstructionType value) {
        this.accountConstruction = value;
    }

    /**
     * Gets the value of the id property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getId() {
        return id;
    }

    /**
     * Sets the value of the id property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setId(String value) {
        this.id = value;
    }

	/* (non-Javadoc)
	 * @see com.evolveum.midpoint.prism.Containerable#asPrismContainerValue()
	 */
	@Override
	public PrismContainerValue asPrismContainerValue() {
		throw new UnsupportedOperationException();
	}

	/* (non-Javadoc)
	 * @see com.evolveum.midpoint.prism.Containerable#setupContainerValue(com.evolveum.midpoint.prism.PrismContainerValue)
	 */
	@Override
	public void setupContainerValue(PrismContainerValue container) {
		throw new UnsupportedOperationException();
	}

}