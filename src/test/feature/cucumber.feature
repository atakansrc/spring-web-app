Feature: Edit Item
	Scenario: Edit Last Item
		When URL is localhost
		Then Find products tab and click
		Then Select last item at table
		And Edit attributes
		Then Edit is successfull.