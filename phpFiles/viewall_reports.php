<?php
	require_once 'connection.php'; //php file for displaying all reports 
	header('Content-Type: application/json');
	class ViewAllReports
	{
		private $db;
		private $connection;
		function __construct()
		{
			$this->db = new DB_Connection();
			$this->connection = $this->db->get_connection();
		}
         
         function does_report_exist()
		{
			$query = "select CONCAT(u.first_name, ' ', u.last_name) AS FullName, u.profile_picture, r.latitude, r.longitude, 
		 r.descriptions, r.date_reported, r.report_picture1, r.status, r.commends, s.situation_name from users u join 		reports r 
			on (u.user_id=r.user_id)
            join situation s
            on (s.situation_id=r.situation_id)
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
	$viewOrders = new ViewAllReports();
	if(isset($_GET['username']))
	{
		$username = $_GET['username'];
		$viewOrders -> does_report_exist();
	}
?> 				