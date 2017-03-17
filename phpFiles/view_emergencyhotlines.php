<?php
	require_once 'connection.php';
	header('Content-Type: application/json');
	class ViewEmergencyHotlines //use to fetch the emergency hotline numbers that allows authorization to immidiately call the said number when pressed
	{
		private $db;
		private $connection;
		function __construct()
		{
			$this->db = new DB_Connection();
			$this->connection = $this->db->get_connection();
		}
         
         function emergencyhotline()
		{
			$query = "select hotline_name, hotline_number from emergency_hotlines";
			$result = mysqli_query($this->connection,$query);
			$json = array();
			if($result === FALSE) 
			{ 
				die(mysql_error());
			}
			else if(mysqli_num_rows($result))
			{
				while($row=mysqli_fetch_assoc($result))
				{
					$json[]=$row;
				}
			}
			echo json_encode($json);
			mysqli_close($this->connection);
		}
	}
	$viewHotlines = new ViewEmergencyHotlines();
	$viewHotlines -> emergencyhotline();
?> 				