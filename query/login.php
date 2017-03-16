<?php
	require_once 'connection.php';
	header('Content-Type: application/json');
	class Login
	{
		private $db;
		private $connection;
		function __construct()
		{
			$this->db = new DB_Connection();
			$this->connection = $this->db->get_connection();
		}
         
         function does_user_exist($user,$password)
		{
			$query = "select * from users where user = '$user' and Password = '$password'";
			$result = mysqli_query($this->connection,$query);
                        $row = mysqli_fetch_row($result);
			if(mysqli_num_rows($result) >= 1)
                        {                                
                                $json['success'] = ''.$row[1];
			}else
			{
				$json['error'] = ' Wrong username or password ';
			}
			echo json_encode($json);
			mysqli_close($this->connection);
		}
	}
	$login = new Login();
	if(isset($_POST['username'],$_POST['password']))
	{
		$user = $_POST['username'];
		$password = md5($_POST['password']);
		
		if(!empty($user) && !empty($password))
		{
			$login -> does_user_exist($user,$password);
		}
		else
		{
                        $jsonerror['error2'] = 'You must enter username and password';
			echo json_encode($jsonerror);
		}
	}
?> 												