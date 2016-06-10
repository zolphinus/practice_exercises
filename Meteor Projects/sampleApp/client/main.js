import { Template } from 'meteor/templating';

console.log("test");

import './main.html';


Template.createView.events({
  'submit form'(event) {
	  event.preventDefault();
	  var titleVar = event.target.title.value;
	  var urlVar = event.target.url.value;
	  var descriptionVar = event.target.description.value;
	  var createdAtVar = new Date();
	  var currentEntry = {
		  title: titleVar,
		  url: urlVar,
		  description: descriptionVar,
		  createdAt: createdAtVar
	  };
	  
	  //server method handles adding the entry to the DB
	  Meteor.call('testMethod', currentEntry);
  },
});

Template.listView.helpers({
	'entryTable': function(){
		return EntriesList;
	},
	
	'entrySettings': function () {
    return {
        rowsPerPage: 10,
        showFilter: false,
        showNavigation: 'auto',
        fields: [
            { key: 'title', label: 'Title' },
            { key: 'url', label: 'URL' },
            { key: 'description', label: 'Description' },
            { key: 'createdAt', label: 'Created At' }
        ]
		};
	}
});