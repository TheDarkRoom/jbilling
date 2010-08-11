/*
    jBilling - The Enterprise Open Source Billing System
    Copyright (C) 2003-2009 Enterprise jBilling Software Ltd. and Emiliano Conde

    This file is part of jBilling.

    jBilling is free software: you can redistribute it and/or modify
    it under the terms of the GNU Affero General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    jBilling is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU Affero General Public License for more details.

    You should have received a copy of the GNU Affero General Public License
    along with jBilling.  If not, see <http://www.gnu.org/licenses/>.
*/

/*
 * Generated by XDoclet - Do not edit!
 */
package com.sapienter.jbilling.server.entity;

/**
 * Value object for PaymentInfoChequeEntity.
 */
public class PaymentInfoChequeDTO
   extends java.lang.Object
   implements java.io.Serializable 
{
   private java.lang.Integer id;
   private boolean idHasBeenSet = false;
   private java.lang.String bank;
   private boolean bankHasBeenSet = false;
   private java.lang.String number;
   private boolean numberHasBeenSet = false;
   private java.util.Date date;
   private boolean dateHasBeenSet = false;

   private java.lang.Integer pk;

   public PaymentInfoChequeDTO()
   {
   }

   public PaymentInfoChequeDTO( java.lang.Integer id,java.lang.String bank,java.lang.String number,java.util.Date date )
   {
	  this.id = id;
	  idHasBeenSet = true;
	  this.bank = bank;
	  bankHasBeenSet = true;
	  this.number = number;
	  numberHasBeenSet = true;
	  this.date = date;
	  dateHasBeenSet = true;
	  pk = this.getId();
   }

   //TODO Cloneable is better than this !
   public PaymentInfoChequeDTO( PaymentInfoChequeDTO otherValue )
   {
	  this.id = otherValue.id;
	  idHasBeenSet = true;
	  this.bank = otherValue.bank;
	  bankHasBeenSet = true;
	  this.number = otherValue.number;
	  numberHasBeenSet = true;
	  this.date = otherValue.date;
	  dateHasBeenSet = true;

	  pk = this.getId();
   }

   public java.lang.Integer getPrimaryKey()
   {
	  return pk;
   }

   public void setPrimaryKey( java.lang.Integer pk )
   {
      // it's also nice to update PK object - just in case
      // somebody would ask for it later...
      this.pk = pk;
	  setId( pk );
   }

   public java.lang.Integer getId()
   {
	  return this.id;
   }

   public void setId( java.lang.Integer id )
   {
	  this.id = id;
	  idHasBeenSet = true;

		  pk = id;
   }

   public boolean idHasBeenSet(){
	  return idHasBeenSet;
   }
   public java.lang.String getBank()
   {
	  return this.bank;
   }

   public void setBank( java.lang.String bank )
   {
	  this.bank = bank;
	  bankHasBeenSet = true;

   }

   public boolean bankHasBeenSet(){
	  return bankHasBeenSet;
   }
   public java.lang.String getNumber()
   {
	  return this.number;
   }

   public void setNumber( java.lang.String number )
   {
	  this.number = number;
	  numberHasBeenSet = true;

   }

   public boolean numberHasBeenSet(){
	  return numberHasBeenSet;
   }
   public java.util.Date getDate()
   {
	  return this.date;
   }

   public void setDate( java.util.Date date )
   {
	  this.date = date;
	  dateHasBeenSet = true;

   }

   public boolean dateHasBeenSet(){
	  return dateHasBeenSet;
   }

   public String toString()
   {
	  StringBuffer str = new StringBuffer("{");

	  str.append("id=" + getId() + " " + "bank=" + getBank() + " " + "number=" + getNumber() + " " + "date=" + getDate());
	  str.append('}');

	  return(str.toString());
   }

   /**
    * A Value Object has an identity if the attributes making its Primary Key have all been set. An object without identity is never equal to any other object.
    *
    * @return true if this instance has an identity.
    */
   protected boolean hasIdentity()
   {
	  return idHasBeenSet;
   }

   public boolean equals(Object other)
   {
      if (this == other)
         return true;
	  if ( ! hasIdentity() ) return false;
	  if (other instanceof PaymentInfoChequeDTO)
	  {
		 PaymentInfoChequeDTO that = (PaymentInfoChequeDTO) other;
		 if ( ! that.hasIdentity() ) return false;
		 boolean lEquals = true;
		 if( this.id == null )
		 {
			lEquals = lEquals && ( that.id == null );
		 }
		 else
		 {
			lEquals = lEquals && this.id.equals( that.id );
		 }

		 lEquals = lEquals && isIdentical(that);

		 return lEquals;
	  }
	  else
	  {
		 return false;
	  }
   }

   public boolean isIdentical(Object other)
   {
	  if (other instanceof PaymentInfoChequeDTO)
	  {
		 PaymentInfoChequeDTO that = (PaymentInfoChequeDTO) other;
		 boolean lEquals = true;
		 if( this.bank == null )
		 {
			lEquals = lEquals && ( that.bank == null );
		 }
		 else
		 {
			lEquals = lEquals && this.bank.equals( that.bank );
		 }
		 if( this.number == null )
		 {
			lEquals = lEquals && ( that.number == null );
		 }
		 else
		 {
			lEquals = lEquals && this.number.equals( that.number );
		 }
		 if( this.date == null )
		 {
			lEquals = lEquals && ( that.date == null );
		 }
		 else
		 {
			lEquals = lEquals && this.date.equals( that.date );
		 }

		 return lEquals;
	  }
	  else
	  {
		 return false;
	  }
   }

   public int hashCode(){
	  int result = 17;
      result = 37*result + ((this.id != null) ? this.id.hashCode() : 0);

      result = 37*result + ((this.bank != null) ? this.bank.hashCode() : 0);

      result = 37*result + ((this.number != null) ? this.number.hashCode() : 0);

      result = 37*result + ((this.date != null) ? this.date.hashCode() : 0);

	  return result;
   }

}
