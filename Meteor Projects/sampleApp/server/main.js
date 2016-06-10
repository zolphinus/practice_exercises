import { Meteor } from 'meteor/meteor';


Meteor.startup(() => {
  // code to run on server at startup
  
});

Meteor.methods({
	testMethod: function(currentEntry){
		EntriesList.insert(currentEntry);
	}
});
