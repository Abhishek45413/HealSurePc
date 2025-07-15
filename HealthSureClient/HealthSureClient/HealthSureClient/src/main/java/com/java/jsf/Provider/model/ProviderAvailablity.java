package com.java.jsf.Provider.model;

import java.sql.Time;

public class ProviderAvailablity {
	 private int availabilityId;
	    private String individualProviderId;
	    private String groupProviderId;
	    private String availableDay;  // MON, TUE, etc.
	    private Time startTime;
	    private Time endTime;


		public int getAvailabilityId() {
			return availabilityId;
		}
		public void setAvailabilityId(int availabilityId) {
			this.availabilityId = availabilityId;
		}
		public String getIndividualProviderId() {
			return individualProviderId;
		}
		public void setIndividualProviderId(String individualProviderId) {
			this.individualProviderId = individualProviderId;
		}
		public String getGroupProviderId() {
			return groupProviderId;
		}
		public void setGroupProviderId(String groupProviderId) {
			this.groupProviderId = groupProviderId;
		}
		public String getAvailableDay() {
			return availableDay;
		}
		public void setAvailableDay(String availableDay) {
			this.availableDay = availableDay;
		}
		public Time getStartTime() {
			return startTime;
		}
		public void setStartTime(Time startTime) {
			this.startTime = startTime;
		}
		public Time getEndTime() {
			return endTime;
		}
		public void setEndTime(Time endTime) {
			this.endTime = endTime;
		}

		public ProviderAvailablity(int availabilityId, String individualProviderId, String groupProviderId,
				String availableDay, Time startTime, Time endTime) {
			super();
			this.availabilityId = availabilityId;
			this.individualProviderId = individualProviderId;
			this.groupProviderId = groupProviderId;
			this.availableDay = availableDay;
			this.startTime = startTime;
			this.endTime = endTime;
		}

		public ProviderAvailablity() {
			super();
			// TODO Auto-generated constructor stub
		}


}
