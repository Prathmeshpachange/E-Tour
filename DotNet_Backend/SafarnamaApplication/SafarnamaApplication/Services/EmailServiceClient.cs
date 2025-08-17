using System.Net.Http;
using System.Text;
using System.Threading.Tasks;
using Newtonsoft.Json;

namespace SafarnamaApplication.Services
{
    public class EmailServiceClient
    {
        private readonly HttpClient _httpClient;

        public EmailServiceClient(HttpClient httpClient)
        {
            _httpClient = httpClient;
        }

        public async Task<string> SendEmailAsync(string to, string subject, string body)
        {
            var payload = new
            {
                to,
                subject,
                body
            };

            var json = JsonConvert.SerializeObject(payload);
            var content = new StringContent(json, Encoding.UTF8, "application/json");

            var response = await _httpClient.PostAsync("api/email/send", content);

            return await response.Content.ReadAsStringAsync();
        }
    }
}
