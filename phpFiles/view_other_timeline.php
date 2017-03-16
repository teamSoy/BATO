<?php
	require_once 'connection.php';
	header('Content-Type: application/json');
	class ViewOthers //php file for viewing other timeline.. the query needs the user_id of the chosen user upon clicking their profile picture
	{
		private $db;
		private $connection;
		function __construct()
		{
			$this->db = new DB_Connection();
			$this->connection = $this->db->get_connection();
		}
         
         function does_followedothers_exist($user_id)
		{
			$query = "select u.first_name, u.last_name, u.profile_picture, r.latitude, r.longitude, 
			r.remarks, r.descriptions, r.date_reported, r.report_picture1, r.report_picture2, 
			r.report_picture3, r.status, r.commends from users u join reports r join followers f
			on (u.user_id=r.user_id) where u.user_id = '$user_id';
			order by r.date_reported desc";
			$result = mysqli_query($this->connection, $query);
			$json = array();
			
			if(mysqli_num_rows($result))
			{
				while($row=mysqli_fetch_assoc($result))
				{
					$json[]=$row;
					
				}
			}
			else
			{
				$json['error'] = 'Please check your internet connection';
			}
			echo json_encode($json);
			mysqli_close($this->connection);
		}
	}
	$ViewOthers = new ViewOthers();
	if(isset($_GET['username']))
	{
		$username = $_GET['username'];
		$ViewOthers -> does_followedothers_exist($username);
	}
?> 				