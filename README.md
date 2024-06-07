## Calendar Hub

- Étude de l'existant => 
- Critique de l'existant =>MAPP
- Solution proposée => notre solution 
- Besoins fonctionnels
- Besoins non fonctionnels
- Architecture physique et logique
 
Diag de cas d'utilisation globale
 
users of the app
 
	Admin (have all rights) 
	DRH 
	RH (valider / refuser conges , ) [RH memmber cant accpect their own conges]
	Manager (accept their n-1 demande de conges ) 
	Team lead / (N-1 de manager) accept their team member demande de conges 
	Collaboraters (passer unde demande de conges autorisation ....
	Externels passer de conges
 
 
modules Possible 
	gestion des utilisateur ( auth + equipe( chaque employee doit etre automatiquement affecte a une equipe ) + profils 
	gestion de conges + module d'aide a la decsion RH pour la validation/ refus d'un conges + priorite 
	gestion de reclamtion  
	gestion de feedback employess vis a vis des conges procure 
	gestion du calendrier entreprise (jour ferier de l'annes en cours , journee evenement sans possibilite de conges )
	gestion de criteres d'acceptation/refus de conges 
	...
 
 
 
USE CASES AND ROLES
 
 
	1-User Authentication and Profile Management:
Admin creates user accounts and assigns roles.
Users log in, update their profiles, and manage their passwords.
 
 
	2-Team Management:
Admin creates teams and assigns employees to them automatically.
Managers and Team Leads view their team members and their details.
 
 
	3-Leave Management:
Employees request leaves through the system.
HR members review leave requests and approve or reject them.
Managers approve or reject leave requests of their subordinates.
Team Leads approve or reject leave requests of their team members.
Criteria for leave approval or rejection are applied automatically based on configured rules.
Leave balances are updated after approval.
 
 
	4-HR Decision Support Module:
HR receives recommendations for leave approval or rejection based on predefined criteria and priorities.
HR evaluates recommendations and makes final decisions.
 
 
	5-Reclamation Management:
Employees submit complaints or issues through the system.
HR or designated personnel review and address the complaints.
 
 
	6-Employee Feedback on Leave Process:
Employees provide feedback on their leave experience.
HR analyzes feedback to improve the leave management process.
 
 
	7-Company Calendar Management:
Admin updates company calendar with holidays and special events.
Leave requests cannot be submitted for days marked as company holidays or events.
 
 
	8-External Leave Requests:
External users submit leave requests through the system.
HR or designated personnel review and process external leave requests.
 
 
	9-Reporting and Analytics:
Generate reports on leave usage, approval rates, and other relevant metrics.
Analyze leave patterns to identify trends and make informed decisions.
 
 
	10-Notification and Alerts:
System sends notifications to relevant parties upon leave request submission, approval, or rejection.
Reminders are sent for pending leave requests or upcoming leaves.
 
 
	User:
Attributes: ID, username, password, role
Methods: authenticate(), updateProfile()
 
 
	Employee:
Attributes: ID, name, email, team
Methods: requestLeave(), submitFeedback()
 
 
	Team:
Attributes: ID, name, manager
Methods: addMember(), removeMember()
 
 
	LeaveRequest:
Attributes: ID, employee, start_date, end_date, status
Methods: getStatus(), approve(), reject()
 
 
	HRMember:
Attributes: ID, name, email
Methods: validateLeaveRequest()
 
 
	Manager:
Attributes: ID, name, email, team
Methods: approveLeaveRequest()
 
 
	TeamLead:
Attributes: ID, name, email, team
Methods: approveLeaveRequest()
 
 
	CompanyCalendar:
Attributes: holidays, events
Methods: addHoliday(), addEvent()
 
 
	Reclamation:
Attributes: ID, employee, description, status
Methods: getStatus(), resolve()
 
 
	Feedback:
Attributes: ID, employee, feedback_description
Methods: getFeedback(), analyzeFeedback()
 
 
	DecisionCriteria:
Attributes: ID, criteria_description, priority
Methods: addCriteria(), evaluateCriteria()
 
 
	Notification:
Attributes: ID, recipient, message
Methods: sendNotification()
