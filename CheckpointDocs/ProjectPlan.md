# Project Plan

## Objective: 
Develop and launch MVP1 of Cast And Climate to the public. Focus on core features that allow the user to - 
1. Enter Their location
2. View Customized Fishing Scores
3. See Real Time Weather Data for the Entered Location
4. Save, Modify, and Delete their Favorite Locations

## Key User Stories:
1. As a user, I want to enter my fishing location so that I can see a customized fishing score based on my specific area.
2. As a user, I want to view real-time weather information for my selected fishing spot so that I can plan my trip according to the current conditions.
3. As a user, I want to see the moon phase information integrated into the app so that I can consider its impact on fish behavior when planning my trip.
4. As a user, I want to save my favorite fishing locations in the app so that I can quickly access them for future trips.


## Development Phases 
1. Research and Planning 
   1. Determine APIs to use
   2. Pseudo Code Algorithim
   3. Create main wireframes
2. Development
   1. Setup Dev Environment
      1. Considerations
         1. Will be hosted in AWS
         2. Will use Hibernate
         3. Will use Log4J
         4. Will use AWS Cognito for Auth
   2. Build Empty main pages and configure routing
      1. Index
      2. Profile
      3. Fishing Dashboard
3. Ensure DAOs, and critical functionality pass unit tests
4. Collect User Feedback
5. Fix bugs/Issues Identified from unit testing/user feedback
   