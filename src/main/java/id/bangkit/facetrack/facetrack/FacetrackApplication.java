package id.bangkit.facetrack.facetrack;

import id.bangkit.facetrack.facetrack.entity.Gender;
import id.bangkit.facetrack.facetrack.entity.Problem;
import id.bangkit.facetrack.facetrack.entity.Role;
import id.bangkit.facetrack.facetrack.entity.User;
import id.bangkit.facetrack.facetrack.repository.ProblemRepository;
import id.bangkit.facetrack.facetrack.repository.UserRepository;
import id.bangkit.facetrack.facetrack.service.FileStorageService;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.List;
import java.util.TimeZone;

@SpringBootApplication
public class FacetrackApplication implements CommandLineRunner {
	@Autowired
	PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

	@Resource
	FileStorageService storageService;

	@Autowired
	ProblemRepository problemRepository;

	@Autowired
	UserRepository userRepository;

	public static void main(String[] args) {
		SpringApplication.run(FacetrackApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		storageService.deleteAll();
		storageService.init();
		List<User> users = new ArrayList<>();
		users.add(User.builder().nama("nadim").email("nadimwkwk@gmail.com").gender(Gender.MALE).noTelp("081252347779")
				.password(passwordEncoder.encode("Nadim456*-")).role(Role.USER).build());
		users.add(User.builder().nama("jafar").email("jafar@gmail.com").gender(Gender.FEMALE).noTelp("0811XXXXX")
				.password(passwordEncoder.encode("Jafar123*-")).role(Role.USER).build());
		userRepository.saveAll(users);
		List<Problem> problems = new ArrayList<>();
		problems.add(Problem.builder()
				.nama("Jerawat")
				.deskripsi("Jerawat adalah kondisi kulit yang terjadi ketika folikel rambut Anda tersumbat oleh minyak dan sel kulit mati. Jerawat biasanya muncul di wajah, leher, dada, punggung, dan bahu.")
				.saran("Bersihkan wajah dua kali sehari dengan pembersih yang lembut, hindari menyentuh wajah dengan tangan yang kotor, gunakan produk perawatan yang mengandung benzoyl peroxide atau salicylic acid, dan konsultasikan dengan dokter kulit untuk perawatan lebih lanjut.")
				.build());
		problems.add(Problem.builder()
				.nama("Mata Panda")
				.deskripsi("Mata panda atau lingkaran hitam di bawah mata sering disebabkan oleh kurang tidur, dehidrasi, atau faktor genetik. Mereka bisa membuat wajah terlihat lelah dan kurang segar.")
				.saran("Tidur cukup setidaknya 7-8 jam per malam, minum banyak air untuk menjaga hidrasi, gunakan krim mata yang mengandung vitamin K atau retinol, dan aplikasikan kompres dingin di area mata untuk mengurangi pembengkakan.")
				.build());
		problems.add(Problem.builder()
				.nama("Kerutan")
				.deskripsi("Kerutan adalah lipatan, garis, atau lekukan pada kulit yang terjadi seiring dengan bertambahnya usia. Paparan sinar matahari, merokok, dan ekspresi wajah yang berulang dapat mempercepat munculnya kerutan.")
				.saran("Gunakan tabir surya setiap hari untuk melindungi kulit dari sinar UV, hindari merokok, aplikasikan pelembap yang kaya antioksidan dan asam hialuronat, dan pertimbangkan perawatan seperti retinoid atau prosedur dermatologi seperti botox atau filler.")
				.build());
		problemRepository.saveAll(problems);
	}
}
