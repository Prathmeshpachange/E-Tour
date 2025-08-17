using AutoMapper;
using SafarnamaApplication.DTOs;
using SafarnamaApplication.Repositories;
using SafarnamaApplication.DTOs;

namespace SafarnamaApplication.Services
{
    public class CostService : ICostService
    {
        private readonly ICostRepository _costRepository;
        private readonly IMapper _mapper;

        public CostService(ICostRepository costRepository, IMapper mapper)
        {
            _costRepository = costRepository;
            _mapper = mapper;
        }

        public async Task<CostDTO?> GetCostByPackageIdAsync(int packageId)
        {
            var cost = await _costRepository.GetByPackageIdAsync(packageId);
            return _mapper.Map<CostDTO>(cost);
        }
    }
}
