package org.example;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLDecoder;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class WebsiteDownloader {

    private static final String BASE_URL = "YOUR SITE"; //Write your http/https First step <---------------------------
    private static final Set<String> VISITED_URLS = Collections.synchronizedSet(new HashSet<>());
    private static final Set<String> DOWNLOADED_FILES = Collections.synchronizedSet(new HashSet<>());
    private static final Pattern LINK_PATTERN = Pattern.compile("href=\"([^\"]*)\"", Pattern.CASE_INSENSITIVE);
    /* region */
    private static final Pattern FILE_PATTERN = Pattern.compile("\\.(pdf|djvu|epub|fb2|zip|rar|txt|doc|docx|rtf|html|htm|mp3|mp4|avi|mkv|mov|jpg|jpeg|png|gif|bmp|tiff|psd|ai|eps|" +
            "svg|ppt|pptx|xls|xlsx|csv|xml|json|yml|yaml|java|cpp|c|h|hpp|py|js|css|php|rb|go|rs|swift|kt|dart|sql" +
            "|db|mdb|accdb|odt|ods|odp|7z|tar|gz|bz2|xz|iso|dmg|exe|msi|deb|rpm|apk|torrent|mobi|azw|chm|dwg|dxf|skp|blend|stl|fbx|obj|max|mb|ma|3ds|unity|unreal" +
            "|uproject|love|godot|gamemaker|rpgmaker|construct|scratch|gb|gbc|gba|nes|snes|n64|ps1|ps2|ps3|ps4|ps5|xbox|steam|gog|itch|android|ios|windows|linux|mac|docker|kubernetes|terraform|ansible|puppet|chef|vagrant|virtualbox|vmware|qemu|proxmox|esxi|aws|azure|gcp|digitalocean|linode|vultr|heroku|netlify|vercel|cloudflare|nginx|apache|iis|tomcat|jetty|node|deno|bun|react|vue|angular|svelte|ember|backbone|meteor|django|flask|fastapi|rails|spring|laravel|symfony|express|koa|hapi|adonis|nest|micronaut|quarkus|vertx|play|akka|graphql|grpc|rest|soap|websocket|webrtc|ssh|ftp|sftp|scp|http|https|ssl|tls|ipv4|ipv6|dns|dhcp|vpn|proxy|cdn|waf|firewall|router|switch|bridge|gateway|modem|wifi|bluetooth|nfc|rfid|usb|hdmi|thunderbolt|lightning|type_c|displayport|vga|dvi|svideo|component|composite|scart|hdmi_arc|hdmi_cec|spdif|optical|rca|xlr|trs|ts|midi|dmx|ptz|ndi|srt|rtmp|rtsp|hls|dash|webrtc|sip|voip|codec|h264|h265|vp9|av1|aac|mp3|flac|alac|wav|aiff|ogg|opus|vorbis|wma|ac3|dts|eac3|truehd|dtshd|atmos|dtsx|imax|3d|4k|8k|hdr|dolbyvision|hlg|sdr|bt2020|bt709|srgb|adobe_rgb|prophoto|rec709|rec2020|dci_p3|display_p3|p3|xyz|lab|luv|yuv|rgb|cmyk|hsl|hsv|hex|pantone|ral|ncs|munsell|oil|watercolor|acrylic|gouache|pastel|charcoal|pencil|ink|pen|marker|brush|airbrush|spray|digital|vector|raster|3d|vr|ar|mr|xr|ai|ml|dl|nn|cnn|rnn|gan|transformer|bert|gpt|gpt2|gpt3|gpt4|dalle|midjourney|stable_diffusion|clip|vqgan|stylegan|biggan|cyclegan|pix2pix|super_resolution|style_transfer|object_detection|segmentation|pose_estimation|face_recognition|emotion_recognition|gesture_recognition|speech_recognition|text_to_speech|speech_to_text|machine_translation|summarization|question_answering|sentiment_analysis|topic_modeling|named_entity_recognition|part_of_speech_tagging|dependency_parsing|constituency_parsing|coreference_resolution|word_embeddings|sentence_embeddings|document_embeddings|knowledge_graph|ontology|semantic_web|linked_data|sparql|rdf|owl|skos|shacl|shex|json_ld|microdata|rdfa|graphql|sql|nosql|newsql|key_value|document|column_family|graph|time_series|spatial|vector|search|analytics|etl|elt|data_warehouse|data_lake|data_mart|data_mesh|data_fabric|data_ops|mlops|aiops|devops|gitops|finops|secops|netops|cloudops|sre|platform_engineering|backend|frontend|fullstack|mobile|desktop|web|game|embedded|iot|arduino|raspberrypi|esp32|esp8266|stm32|avr|arm|x86|x64|riscv|mips|powerpc|sparc|alpha|itanium|amd64|intel|amd|nvidia|qualcomm|mediatek|samsung|apple|google|microsoft|amazon|facebook|tesla|spacex|blue_origin|virgin_galactic|nasa|esa|roscosmos|isro|cnsa|jaxa|space|astronomy|astrophysics|cosmology|particle_physics|quantum_mechanics|relativity|string_theory|m_theory|loop_quantum_gravity|causal_sets|twistor_theory|noncommutative_geometry|quantum_computing|quantum_information|quantum_cryptography|quantum_teleportation|quantum_entanglement|quantum_supremacy|quantum_error_correction|topological_quantum_computing|quantum_annealing|quantum_machine_learning|quantum_neural_networks|quantum_algorithm|shor|grover|deutsch_jozsa|simon|quantum_fourier_transform|quantum_phase_estimation|variational_quantum_eigensolver|quantum_approximate_optimization_algorithm|quantum_walk|quantum_random_walk|quantum_coin|quantum_coin_flip|quantum_dice|quantum_card_shuffle|quantum_chess|quantum_go|quantum_poker|quantum_blackjack|quantum_roulette|quantum_lottery|quantum_bingo|quantum_money|quantum_voting|quantum_democracy|quantum_auction|quantum_contract|quantum_law|quantum_ethics|quantum_philosophy|quantum_consciousness|quantum_mind|quantum_brain|quantum_cognition|quantum_psychology|quantum_sociology|quantum_economics|quantum_finance|quantum_marketing|quantum_management|quantum_leadership|quantum_education|quantum_learning|quantum_teaching|quantum_research|quantum_development|quantum_design|quantum_engineering|quantum_architecture|quantum_art|quantum_music|quantum_literature|quantum_poetry|quantum_theater|quantum_dance|quantum_film|quantum_photography|quantum_sculpture|quantum_painting|quantum_drawing|quantum_printmaking|quantum_collage|quantum_assemblage|quantum_installation|quantum_performance|quantum_media|quantum_journalism|quantum_broadcasting|quantum_publishing|quantum_library|quantum_museum|quantum_gallery|quantum_exhibition|quantum_festival|quantum_conference|quantum_seminar|quantum_workshop|quantum_symposium|quantum_congress|quantum_assembly|quantum_summit|quantum_forum|quantum_meeting|quantum_roundtable|quantum_panel|quantum_debate|quantum_discussion|quantum_dialogue|quantum_conversation|quantum_interview|quantum_podcast|quantum_webinar|quantum_tutorial|quantum_demo|quantum_presentation|quantum_speech|quantum_talk|quantum_lecture|quantum_class|quantum_course|quantum_lesson|quantum_module|quantum_unit|quantum_chapter|quantum_section|quantum_part|quantum_volume|quantum_book|quantum_manual|quantum_guide|quantum_tutorial|quantum_howto|quantum_recipe|quantum_formula|quantum_equation|quantum_theorem|quantum_lemma|quantum_corollary|quantum_proposition|quantum_conjecture|quantum_hypothesis|quantum_theory|quantum_law|quantum_principle|quantum_rule|quantum_axiom|quantum_postulate|quantum_definition|quantum_example|quantum_exercise|quantum_problem|quantum_solution|quantum_answer|quantum_key|quantum_hint|quantum_tip|quantum_trick|quantum_cheat|quantum_shortcut|quantum_workaround|quantum_fix|quantum_patch|quantum_update|quantum_upgrade|quantum_downgrade|quantum_rollback|quantum_restore|quantum_backup|quantum_recovery|quantum_rescue|quantum_salvage|quantum_retrieve|quantum_fetch|quantum_get|quantum_obtain|quantum_acquire|quantum_procure|quantum_secure|quantum_ensure|quantum_guarantee|quantum_warranty|quantum_promise|quantum_pledge|quantum_vow|quantum_oath|quantum_swear|quantum_affirm|quantum_assert|quantum_declare|quantum_proclaim|quantum_announce|quantum_publish|quantum_share|quantum_distribute|quantum_disseminate|quantum_spread|quantum_circulate|quantum_broadcast|quantum_telecast|quantum_webcast|quantum_podcast|quantum_stream|quantum_live|quantum_real_time|quantum_now|quantum_today|quantum_present|quantum_current|quantum_contemporary|quantum_modern|quantum_future|quantum_futuristic|quantum_advanced|quantum_cutting_edge|quantum_bleeding_edge|quantum_state_of_the_art|quantum_groundbreaking|quantum_revolutionary|quantum_innovative|quantum_creative|quantum_original|quantum_novel|quantum_fresh|quantum_new|quantum_latest|quantum_recent|quantum_up_to_date|quantum_timely|quantum_punctual|quantum_precise|quantum_accurate|quantum_exact|quantum_perfect|quantum_ideal|quantum_optimal|quantum_best|quantum_greatest|quantum_superior|quantum_excellent|quantum_outstanding|quantum_remarkable|quantum_extraordinary|quantum_amazing|quantum_astonishing|quantum_astounding|quantum_surprising|quantum_shocking|quantum_stunning|quantum_breathtaking|quantome|mobi|azw3|prc|lit|pdb|pml|rb|cbz|cbr|cb7|cbt|cba|djvu|djv)$", Pattern.CASE_INSENSITIVE);

    static {
        // Trust all SSL certificates
        TrustManager[] trustAllCerts = new TrustManager[]{
                new X509TrustManager() {
                    public java.security.cert.X509Certificate[] getAcceptedIssuers() { return null; }
                    public void checkClientTrusted(java.security.cert.X509Certificate[] certs, String authType) {}
                    public void checkServerTrusted(java.security.cert.X509Certificate[] certs, String authType) {}
                }
        };

        try {
            SSLContext sc = SSLContext.getInstance("SSL");
            sc.init(null, trustAllCerts, new java.security.SecureRandom());
            HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
            HttpsURLConnection.setDefaultHostnameVerifier((hostname, session) -> true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        System.out.println("Starting download from: " + BASE_URL);
        downloadRecursive(BASE_URL, "downloads");
        System.out.println("Download completed! Total files: " + DOWNLOADED_FILES.size());
    }

    private static void downloadRecursive(String url, String localPath) {
        if (VISITED_URLS.contains(url)) return;
        VISITED_URLS.add(url);

        try {
            System.out.println("Processing: " + url);

            String htmlContent = getHtmlContent(url);
            if (htmlContent == null) return;

            List<String> links = extractLinks(htmlContent);

            // Create local directory (default)
            // Specify your directory  |  Second step(optional)   <------------------------------------------------
            File localDir = new File(localPath);
            if (!localDir.exists()) localDir.mkdirs();

            for (String link : links) {
                String absoluteUrl = makeAbsoluteUrl(url, link);

                if (isFileLink(link)) {
                    downloadFile(absoluteUrl, link, localPath);
                } else if (isDirectoryLink(link, absoluteUrl)) {
                    String folderName = extractFolderName(link);
                    String newLocalPath = localPath + File.separator + folderName;
                    downloadRecursive(absoluteUrl, newLocalPath);
                }
            }

        } catch (Exception e) {
            System.err.println("Error processing URL: " + url + " - " + e.getMessage());
        }
    }

    private static String extractFolderName(String link) {
        // Remove trailing slash and decode URL
        String name = link.replace("/", "");
        try {
            return URLDecoder.decode(name, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            return name;
        }
    }

    private static String getHtmlContent(String urlString) {
        try {
            URL url = new URL(urlString);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36");
            conn.setConnectTimeout(30000);
            conn.setReadTimeout(30000);

            // Detect encoding from headers or use UTF-8 as default
            String charset = "UTF-8";
            String contentType = conn.getContentType();
            if (contentType != null) {
                String[] values = contentType.split(";");
                for (String value : values) {
                    value = value.trim();
                    if (value.toLowerCase().startsWith("charset=")) {
                        charset = value.substring("charset=".length());
                    }
                }
            }

            BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream(), charset));
            StringBuilder content = new StringBuilder();
            String line;

            while ((line = reader.readLine()) != null) {
                content.append(line).append("\n");
            }

            reader.close();
            conn.disconnect();

            return content.toString();

        } catch (Exception e) {
            System.err.println("Failed to get content from: " + urlString + " - " + e.getMessage());
            return null;
        }
    }

    private static List<String> extractLinks(String htmlContent) {
        List<String> links = new ArrayList<>();
        Matcher matcher = LINK_PATTERN.matcher(htmlContent);

        while (matcher.find()) {
            String link = matcher.group(1);
            if (!link.isEmpty() && !link.startsWith("#") && !link.startsWith("mailto:") && !link.startsWith("javascript:")) {
                links.add(link);
            }
        }

        return links;
    }

    private static String makeAbsoluteUrl(String baseUrl, String relativeUrl) {
        try {
            // Handle absolute URLs
            if (relativeUrl.startsWith("http://") || relativeUrl.startsWith("https://")) {
                return relativeUrl;
            }

            URL base = new URL(baseUrl);
            URL absolute = new URL(base, relativeUrl);
            return absolute.toString();
        } catch (Exception e) {
            System.err.println("Error making absolute URL from: " + baseUrl + " + " + relativeUrl);
            return relativeUrl;
        }
    }

    private static boolean isFileLink(String link) {
        // Check if it's a file (has extension and not a directory)
        return FILE_PATTERN.matcher(link).find() && !link.endsWith("/");
    }

    private static boolean isDirectoryLink(String link, String absoluteUrl) {
        // It's a directory if it ends with slash, is not a file, and is within our target domain
        return (link.endsWith("/") || !link.contains(".")) &&
                !link.equals("../") &&
                !link.contains("?") &&
                !link.contains("://") &&
                absoluteUrl.startsWith(BASE_URL) &&
                !isFileLink(link);
    }

    private static void downloadFile(String fileUrl, String fileName, String localPath) {
        if (DOWNLOADED_FILES.contains(fileUrl)) return;

        try {
            // Create local directory
            File outputDir = new File(localPath);
            if (!outputDir.exists()) outputDir.mkdirs();

            // Clean filename from URL encoding
            String cleanFileName;
            try {
                cleanFileName = URLDecoder.decode(fileName, "UTF-8");
            } catch (UnsupportedEncodingException e) {
                cleanFileName = fileName;
            }

            // Extract just the filename without path
            String simpleFileName = new File(cleanFileName).getName();
            File outputFile = new File(outputDir, simpleFileName);

            System.out.println("Downloading: " + simpleFileName + " to " + localPath);

            URL url = new URL(fileUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36");

            try (InputStream in = conn.getInputStream();
                 FileOutputStream out = new FileOutputStream(outputFile)) {

                byte[] buffer = new byte[4096];
                int bytesRead;

                while ((bytesRead = in.read(buffer)) != -1) {
                    out.write(buffer, 0, bytesRead);
                }
            }

            DOWNLOADED_FILES.add(fileUrl);
            System.out.println("Downloaded: " + simpleFileName + " (" + outputFile.length() + " bytes)");

            Thread.sleep(200);

        } catch (Exception e) {
            System.err.println("Failed to download: " + fileUrl + " - " + e.getMessage());
        }
    }
}