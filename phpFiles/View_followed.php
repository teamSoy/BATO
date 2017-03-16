<?php
	require_once 'connection.php';
	header('Content-Type: application/json');
	class ViewTimeline // used for filtering your feed to only display the post of your followed users
	{
		private $db;
		private $connection;
		function __construct()
		{
			$this->db = new DB_Connection();
			$this->connection = $this->db->get_connection();
		}
         
         function does_viewtimeline_exist($username)
		{
						$query = "select u.first_name, u.last_name, u.profile_picture, r.latitude, r.longitude, 
			r.remarks, r.descriptions, r.date_reported, r.report_picture1, r.report_picture2, 
			r.report_picture3, r.status, r.commends from users u join reports r 
			on (u.user_id=r.user_id)
			join followers f
			on (f.user_id2=u.user_id)
			where f.user_id = (select user_id from users where username ='$username')
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
	$viewOrders = new ViewTimeline();
	if(isset($_GET['username']))
	{
		$username = $_GET['username'];
		$viewOrders -> does_viewtimeline_exist($username);
	}
?> 				